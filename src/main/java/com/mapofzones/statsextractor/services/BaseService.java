package com.mapofzones.statsextractor.services;

import com.mapofzones.statsextractor.data.repository.BaseRepository;
import com.mapofzones.statsextractor.utils.time.Interval;
import com.mapofzones.statsextractor.utils.time.IntervalCalculation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BaseService<R extends BaseRepository<E>, E> implements IService<R, E> {

    private final R repository;
    private final LocalDateTime start;
    private final Integer period;

    public BaseService(R repository, LocalDateTime start, Integer period) {
        this.repository = repository;
        this.start = start;
        this.period = period;
    }

    public List<E> getDataList(LocalDateTime now) {

        List<E> dataList = new ArrayList<>();
        List<Interval> intervalList = IntervalCalculation.getIntervalList(start, now, period);

        for (Interval interval : intervalList)
            dataList.addAll(repository.read(interval.getStart(), interval.getEnd()));

        return dataList;
    }

    @Override
    public void deleteData() {
        repository.deleteAll();
    }

    @Override
    public void writeData(List<E> dataList) {
        repository.writeAll(dataList);
    }
}