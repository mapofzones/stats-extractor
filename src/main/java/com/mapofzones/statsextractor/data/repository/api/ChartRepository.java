package com.mapofzones.statsextractor.data.repository.api;

import com.mapofzones.statsextractor.data.Queries;
import com.mapofzones.statsextractor.data.domain.Chart;
import com.mapofzones.statsextractor.data.domain.ChartMapping;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ChartRepository extends BaseApiRepository<Chart> implements IBaseApiRepository<Chart> {

    public ChartRepository(@Qualifier("apiJdbcTemplate") JdbcTemplate apiJdbcTemplate, Queries queries) {
        super(apiJdbcTemplate, ChartMapping.getPreparedStatementSetter(), queries.getInsertChartSQL(), queries.getDeleteChart());
    }
}
