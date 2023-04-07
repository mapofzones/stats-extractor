package com.mapofzones.statsextractor.data.repository;

import java.util.List;

public interface IBaseRepository<E> {

    List<E> read(Object... params);
    void deleteAll();
    void writeAll(List<E> dataList);

}
