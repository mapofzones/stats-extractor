package com.mapofzones.statsextractor.config;

import com.mapofzones.statsextractor.data.Queries;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataConfig {

    @Bean
    public Queries queries() {
        return new Queries();
    }

    // Core database config
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.core")
    public DataSource coreDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate coreJdbcTemplate(@Qualifier("coreDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    // Core database config
    @Bean
    @ConfigurationProperties("spring.datasource.api")
    public DataSource apiDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate apiJdbcTemplate(@Qualifier("apiDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
