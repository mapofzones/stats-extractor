package com.mapofzones.statsextractor.data.repository.core;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class BaseCoreRepository<E> implements IBaseCoreRepository<E> {

    private final JdbcTemplate corejJdbcTemplate;
    private final RowMapper<E> mapper;
    private final String readQuery;

    public BaseCoreRepository(JdbcTemplate corejJdbcTemplate,
                              RowMapper<E> mapper,
                              String readQuery) {
        this.mapper = mapper;
        this.corejJdbcTemplate = corejJdbcTemplate;
        this.readQuery = readQuery;
    }

    @Override
    public List<E> read(Object... params) {
        return corejJdbcTemplate.query(readQuery, mapper, params);
    }
}
