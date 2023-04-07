package com.mapofzones.statsextractor.data.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class BaseRepository<E> implements IBaseRepository<E> {

    private final RowMapper<E> mapper;
    private final ParameterizedPreparedStatementSetter<E> preparedStatementSetter;
    private final JdbcTemplate corejJdbcTemplate;
    private final JdbcTemplate apiJdbcTemplate;
    private final String readQuery;
    private final String deleteQuery;
    private final String writeQuery;

    public BaseRepository(JdbcTemplate corejJdbcTemplate,
                          JdbcTemplate apiJdbcTemplate,
                          RowMapper<E> mapper,
                          ParameterizedPreparedStatementSetter<E> preparedStatementSetter,
                          String readQuery,
                          String deleteQuery,
                          String writeQuery) {
        this.corejJdbcTemplate = corejJdbcTemplate;
        this.apiJdbcTemplate = apiJdbcTemplate;
        this.mapper = mapper;
        this.preparedStatementSetter = preparedStatementSetter;
        this.readQuery = readQuery;
        this.deleteQuery = deleteQuery;
        this.writeQuery = writeQuery;
    }

    @Transactional(readOnly = true)
    public List<E> read(Object... params) {
        return corejJdbcTemplate.query(readQuery, mapper, params);
    }

    @Transactional
    public void deleteAll() {
        apiJdbcTemplate.execute(deleteQuery);
    }

    @Transactional
    public void writeAll(List<E> dataList) {
        apiJdbcTemplate.batchUpdate(writeQuery, dataList, dataList.size(), preparedStatementSetter);
    }
}
