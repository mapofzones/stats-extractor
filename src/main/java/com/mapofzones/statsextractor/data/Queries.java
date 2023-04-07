package com.mapofzones.statsextractor.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
public class Queries {

    @Value("classpath:queries/cashflow/find_cashflow_chart.sql")
    private Resource cashflowSQL;

    @Value("classpath:queries/cashflow/find_transfers_chart.sql")
    private Resource transfersSQL;

    @Value("classpath:queries/cashflow/insert_chart.sql")
    private Resource insertChart;

    @Value("classpath:queries/cashflow/delete_chart.sql")
    private Resource deleteChart;

    public String getCashflowSQL() {
        return getContentAsString(cashflowSQL);
    }

    public String getInsertChartSQL() {
        return getContentAsString(insertChart);
    }

    public String getDeleteChart() {
        return getContentAsString(deleteChart);
    }

    public String getTransfersSQL() {
        return getContentAsString(transfersSQL);
    }

    private String getContentAsString(Resource resource) {
        try {
            return resource.getContentAsString(Charset.defaultCharset());
        } catch (IOException e) {
            log.error("Cant read sql file. {}", e.toString());
            throw new RuntimeException(e);
        }
    }
}
