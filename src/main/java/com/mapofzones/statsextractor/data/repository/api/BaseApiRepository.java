package com.mapofzones.statsextractor.data.repository.api;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import java.util.List;

public class BaseApiRepository<E> implements IBaseApiRepository<E> {

    private final JdbcTemplate apiJdbcTemplate;
    private final ParameterizedPreparedStatementSetter<E> preparedStatementSetter;
    private final String deleteQuery;
    private final String writeQuery;


    public BaseApiRepository(JdbcTemplate apiJdbcTemplate, ParameterizedPreparedStatementSetter<E> preparedStatementSetter,
                             String writeQuery, String deleteQuery) {
        this.apiJdbcTemplate = apiJdbcTemplate;
        this.preparedStatementSetter = preparedStatementSetter;
        this.writeQuery = writeQuery;
        this.deleteQuery = deleteQuery;
    }

    public void writeAll(List<E> dataList) {
        apiJdbcTemplate.batchUpdate(writeQuery, dataList, dataList.size(), preparedStatementSetter);
    }

    public void deleteAll() {
        apiJdbcTemplate.update(deleteQuery);
    }
}
