package com.mapofzones.statsextractor.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
public class Queries {

    @Value("classpath:queries/find_cashflow_chart.sql")
    private Resource cashflowSQL;

    @Value("classpath:queries/find_transfers_chart.sql")
    private Resource transfersSQL;

    @Value("classpath:queries/find_mau_chart.sql")
    private Resource mauSQL;

    @Value("classpath:queries/find_transfers_chart.sql")
    private Resource dauSQL;

    @Value("classpath:queries/find_transactions_chart.sql")
    private Resource transactionsSQL;

    @Value("classpath:queries/insert_chart.sql")
    private Resource insertChart;

    @Value("classpath:queries/delete_chart.sql")
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

    public String getMauSQL() {
        return getContentAsString(mauSQL);
    }

    public String getDauSQL() {
        return getContentAsString(dauSQL);
    }

    public String getTransactionsSQL() {
        return getContentAsString(transactionsSQL);
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
