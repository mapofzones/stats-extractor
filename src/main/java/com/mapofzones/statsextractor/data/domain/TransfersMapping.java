package com.mapofzones.statsextractor.data.domain;

import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

public class TransfersMapping {

    public static RowMapper<Transfers> getMapper() {
        return (rs, rowNum) -> {
            Transfers transfers = new Transfers();
            transfers.setBlockchain(rs.getString(1));
            transfers.setNetworkId(rs.getString(2));
            transfers.setChartType(rs.getString(3));
            transfers.setPointIndex(rs.getLong(4));
            transfers.setPointValue(rs.getBigDecimal(5));
            return transfers;
        };
    }

    public static ParameterizedPreparedStatementSetter<Transfers> getPreparedStatementSetter() {
        return (ps, argument) -> {
            ps.setString(1, argument.getBlockchain());
            ps.setString(2, argument.getNetworkId());
            ps.setString(3, argument.getChartType());
            ps.setLong(4, argument.getPointIndex());
            ps.setBigDecimal(5, argument.getPointValue());
        };
    }

}
