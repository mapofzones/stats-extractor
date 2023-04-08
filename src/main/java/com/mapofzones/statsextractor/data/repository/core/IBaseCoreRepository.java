package com.mapofzones.statsextractor.data.repository.core;

import java.util.List;

public interface IBaseCoreRepository<E> {

    List<E> read(Object... params);

}
