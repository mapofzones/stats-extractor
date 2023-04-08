with dataset as (
    select
        stats.zone as network_id,
        DATE_TRUNC('day', stats.hour) as hour,
        sum(txs_cnt)::bigint as txs_cnt
    from
        total_tx_hourly_stats as stats
    WHERE stats.hour >= ? and stats.hour < ?
    group by
        stats.zone,
        DATE_TRUNC('day', stats.hour)
)

SELECT
    zones.name as blockchain,
    dataset.network_id,
    'transactions' as chart_type,
    cast(extract(epoch from dataset.hour) as integer) as point_index,
    dataset.txs_cnt as point_value
FROM
    dataset
        LEFT JOIN public.zones ON dataset.network_id = zones.chain_id
