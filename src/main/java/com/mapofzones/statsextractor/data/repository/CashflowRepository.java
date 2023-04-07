package com.mapofzones.statsextractor.data.repository;

import com.mapofzones.statsextractor.data.Queries;
import com.mapofzones.statsextractor.data.domain.Cashflow;
import com.mapofzones.statsextractor.data.domain.CashflowMapping;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CashflowRepository extends BaseRepository<Cashflow> implements IBaseRepository<Cashflow> {

    public CashflowRepository(@Qualifier("coreJdbcTemplate") JdbcTemplate corejJdbcTemplate,
                              @Qualifier("apiJdbcTemplate") JdbcTemplate apiJdbcTemplate,
                              Queries queries) {
        super(corejJdbcTemplate,
                apiJdbcTemplate,
                CashflowMapping.getMapper(),
                CashflowMapping.getPreparedStatementSetter(),
                queries.getCashflowSQL(),
                queries.getDeleteChart(),
                queries.getInsertChartSQL());
    }
}
