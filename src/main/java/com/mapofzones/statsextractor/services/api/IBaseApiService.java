package com.mapofzones.statsextractor.services.api;

import com.mapofzones.statsextractor.data.repository.api.IBaseApiRepository;

import java.util.List;

public interface IBaseApiService <R extends IBaseApiRepository<E>, E> {

    void writeData(List<E> dataList);
    void deleteData();

}