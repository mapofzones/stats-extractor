with dataset as (
    SELECT
        zone as network_id,
        'dau' as chart_type,
        cast(extract(epoch from DATE_TRUNC('day', stats.hour)) as integer) as point_index,
        COUNT(DISTINCT address) as point_value
    FROM
        public.active_addresses as stats
    WHERE
            stats.is_internal_tx = true
      and stats.hour >= ? and stats.hour < ?
    GROUP BY
        zone,
        DATE_TRUNC('day', stats.hour)
)

SELECT
    zones.name as blockchain,
    dataset.network_id,
    dataset.chart_type,
    dataset.point_index,
    dataset.point_value
FROM
    dataset
        LEFT JOIN public.zones ON zones.chain_id = dataset.network_id
