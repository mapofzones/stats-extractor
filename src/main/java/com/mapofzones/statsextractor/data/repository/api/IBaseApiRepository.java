package com.mapofzones.statsextractor.data.repository.api;

import java.util.List;

public interface IBaseApiRepository<E> {

    void deleteAll();
    void writeAll(List<E> dataList);

}
