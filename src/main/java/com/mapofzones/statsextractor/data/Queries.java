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

    @Value("classpath:queries/insert_cashflow_chart.sql")
    private Resource insertChart;

    @Value("classpath:queries/delete_cashflow_chart.sql")
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

    private String getContentAsString(Resource resource) {
        try {
            return resource.getContentAsString(Charset.defaultCharset());
        } catch (IOException e) {
            log.error("Cant read sql file. {}", e.toString());
            throw new RuntimeException(e);
        }
    }
}
