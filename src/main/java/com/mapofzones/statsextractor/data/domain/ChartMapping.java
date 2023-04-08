package com.mapofzones.statsextractor.data.domain;

import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

public class ChartMapping {

    public static RowMapper<Chart> getMapper() {
        return (rs, rowNum) -> {
            Chart chart = new Chart();
            chart.setBlockchain(rs.getString(1));
            chart.setNetworkId(rs.getString(2));
            chart.setChartType(rs.getString(3));
            chart.setPointIndex(rs.getLong(4));
            chart.setPointValue(rs.getBigDecimal(5));
            return chart;
        };
    }

    public static ParameterizedPreparedStatementSetter<Chart> getPreparedStatementSetter() {
        return (ps, argument) -> {
            ps.setString(1, argument.getBlockchain());
            ps.setString(2, argument.getNetworkId());
            ps.setString(3, argument.getChartType());
            ps.setLong(4, argument.getPointIndex());
            ps.setBigDecimal(5, argument.getPointValue());
        };
    }
}
