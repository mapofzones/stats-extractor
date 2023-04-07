package com.mapofzones.statsextractor.data.domain;

import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

public class CashflowMapping {

    public static RowMapper<Cashflow> getMapper() {
        return (rs, rowNum) -> {
            Cashflow cashflow = new Cashflow();
            cashflow.setBlockchain(rs.getString(1));
            cashflow.setNetworkId(rs.getString(2));
            cashflow.setChartType(rs.getString(3));
            cashflow.setPointIndex(rs.getLong(4));
            cashflow.setPointValue(rs.getBigDecimal(5));
            return cashflow;
        };
    }

    public static ParameterizedPreparedStatementSetter<Cashflow> getPreparedStatementSetter() {
        return (ps, argument) -> {
            ps.setString(1, argument.getBlockchain());
            ps.setString(2, argument.getNetworkId());
            ps.setString(3, argument.getChartType());
            ps.setLong(4, argument.getPointIndex());
            ps.setBigDecimal(5, argument.getPointValue());
        };
    }
}
