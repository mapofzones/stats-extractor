with
    last_known_price as (
        SELECT DISTINCT on (zone, base_denom)
            zone,
            base_denom,
            coingecko_symbol_price_in_usd as coingecko,
            osmosis_symbol_price_in_usd as osmosis
        FROM
            public.token_prices
        WHERE
            coingecko_symbol_price_in_usd is NOT NULL
           or osmosis_symbol_price_in_usd is NOT NULL
        ORDER BY
            zone,
            base_denom,
            datetime DESC
    )
   , cashflow_stats as (
    select
        stats.zone as network_id,
        stats.zone_src as source,
        stats.zone_dest as target,
        stats.ibc_channel as channel,
        DATE_TRUNC('day', stats.hour) as hour,
        coalesce(sum(
                             (stats.amount / POWER(10, tokens.symbol_point_exponent))::bigint *
                             coalesce(prices.coingecko_symbol_price_in_usd, prices.osmosis_symbol_price_in_usd, last_known_price.coingecko, last_known_price.osmosis, 0)
                     ), 0)::bigint as usd_cashflow
    from
        ibc_transfer_hourly_cashflow as stats
            inner join derivatives
                       on stats.zone = derivatives.zone and stats.derivative_denom = derivatives.full_denom
            inner join tokens
                       on derivatives.base_denom = tokens.base_denom and derivatives.origin_zone = tokens.zone
            left join token_prices as prices
                      on prices.zone = tokens.zone and prices.base_denom = tokens.base_denom and prices.datetime = stats.hour
            left join zones as src on src.chain_id = stats.zone_src
            left join zones as dest on dest.chain_id = stats.zone_dest
            left join last_known_price on last_known_price.zone = tokens.zone and last_known_price.base_denom = tokens.base_denom
    group by
        stats.zone,
        stats.zone_src,
        stats.zone_dest,
        stats.ibc_channel,
        DATE_TRUNC('day', stats.hour)
)

   , cashflow_stats_in as (
    select
        target as network_id,
        source as counterparty_network_id,
        hour,
        sum(usd_cashflow) as ibc_cashflow_in
    from
        cashflow_stats as stats
    where
            stats.network_id = stats.target
    group by
        target,
        source,
        hour
)
   , cashflow_stats_out as (
    select
        source as network_id,
        target as counterparty_network_id,
        hour,
        sum(usd_cashflow) as ibc_cashflow_out
    from
        cashflow_stats as stats
    where
            stats.network_id = stats.source
    group by
        source,
        target,
        hour
)
   , counters_dataset as (
    select
        coalesce(cf_out.network_id, cf_in.network_id, out.network_id, out_internal.network_id) as network_id,
        coalesce(cf_out.hour, cf_in.hour, out.hour, out_internal.hour) as day,

        coalesce(cf_out.ibc_cashflow_out, 0) as ibc_cashflow_in_external,
        coalesce(cf_in.ibc_cashflow_in, 0) as ibc_cashflow_in,


        coalesce(out_internal.ibc_cashflow_out, 0) as ibc_cashflow_out_internal,
        coalesce(out.ibc_cashflow_in, 0) as ibc_cashflow_out
    from
        cashflow_stats_out as cf_out
            FULL OUTER JOIN cashflow_stats_in as cf_in
                            ON cf_out.network_id = cf_in.network_id
                                and cf_out.hour = cf_in.hour
                                and cf_out.counterparty_network_id = cf_in.counterparty_network_id

            FULL OUTER JOIN cashflow_stats_in as out
                            ON cf_in.network_id = out.counterparty_network_id -- out.network_id
                                and cf_in.hour = out.hour
                                and cf_in.counterparty_network_id = out.network_id -- out.counterparty_network_id

            FULL OUTER JOIN cashflow_stats_out as out_internal
                            ON out_internal.network_id = out.network_id
                                and out_internal.hour = out.hour
                                and out_internal.counterparty_network_id = out.counterparty_network_id
)
   , combined_dataset as (
    select
        zones.name as blockchain,
        network_id,
        day,
        ibc_cashflow_in,
        ibc_cashflow_out
    from (
             SELECT
                 network_id,
                 day,
                 sum(ibc_cashflow_in) as ibc_cashflow_in,
                 sum(ibc_cashflow_out) as ibc_cashflow_out
             from
                 counters_dataset
             WHERE day >= ? and day < ?
             GROUP BY
                 network_id,
                 day
         ) as a
             left join public.zones on a.network_id = zones.chain_id
)

SELECT
    blockchain,
    network_id,
    'ibc_cashflow_in' as chart_type,
    cast(extract(epoch from day) as integer) as point_index,
    ibc_cashflow_in as point_value
FROM
    combined_dataset
UNION All
SELECT
    blockchain,
    network_id,
    'ibc_cashflow_out' as chart_type,
    cast(extract(epoch from day) as integer) as point_index,
    ibc_cashflow_out as point_value
FROM
    combined_dataset
UNION All
SELECT
    blockchain,
    network_id,
    'ibc_cashflow' as chart_type,
    cast(extract(epoch from day) as integer) as point_index,
    (ibc_cashflow_in + ibc_cashflow_out)::numeric as point_value
FROM
    combined_dataset
