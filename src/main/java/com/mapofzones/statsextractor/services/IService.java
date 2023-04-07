package com.mapofzones.statsextractor.services;

import com.mapofzones.statsextractor.data.repository.IBaseRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IService<R extends IBaseRepository<E>, E> {

    List<E> getDataList(LocalDateTime now);
    void deleteData();
    void writeData(List<E> dataList);

}
