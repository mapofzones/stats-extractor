package com.mapofzones.statsextractor.services.api;

import com.mapofzones.statsextractor.data.repository.api.IBaseApiRepository;

import java.util.List;

public class BaseApiService<R extends IBaseApiRepository<E>, E> implements IBaseApiService <R, E> {

    private final R apiRepository;

    public BaseApiService (R apiRepository) {
        this.apiRepository = apiRepository;
    }

    @Override
    public void writeData(List<E> dataList) {
        apiRepository.writeAll(dataList);
    }

    @Override
    public void deleteData() {
        apiRepository.deleteAll();
    }
}
