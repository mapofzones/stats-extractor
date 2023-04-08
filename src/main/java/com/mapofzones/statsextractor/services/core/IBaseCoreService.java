package com.mapofzones.statsextractor.services.core;

import com.mapofzones.statsextractor.data.repository.core.IBaseCoreRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IBaseCoreService<R extends IBaseCoreRepository<E>, E> {

    List<E> getDataList(LocalDateTime now);

}
