package com.mapofzones.statsextractor.data.repository;

import com.mapofzones.statsextractor.data.Queries;
import com.mapofzones.statsextractor.data.domain.Transfers;
import com.mapofzones.statsextractor.data.domain.TransfersMapping;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransfersRepository extends BaseRepository<Transfers> implements IBaseRepository<Transfers>{

    public TransfersRepository(@Qualifier("coreJdbcTemplate") JdbcTemplate corejJdbcTemplate,
                               @Qualifier("apiJdbcTemplate") JdbcTemplate apiJdbcTemplate,
                               Queries queries) {
        super(corejJdbcTemplate,
                apiJdbcTemplate,
                TransfersMapping.getMapper(),
                TransfersMapping.getPreparedStatementSetter(),
                queries.getTransfersSQL(),
                queries.getDeleteChart(),
                queries.getInsertChartSQL());
    }
}
