package com.mapofzones.statsextractor.data.repository.core;

import com.mapofzones.statsextractor.data.Queries;
import com.mapofzones.statsextractor.data.domain.Chart;
import com.mapofzones.statsextractor.data.domain.ChartMapping;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionsRepository extends BaseCoreRepository<Chart> implements IBaseCoreRepository<Chart> {

    public TransactionsRepository(@Qualifier("coreJdbcTemplate") JdbcTemplate corejJdbcTemplate, Queries queries) {
        super(corejJdbcTemplate, ChartMapping.getMapper(), queries.getTransactionsSQL());
    }
}
