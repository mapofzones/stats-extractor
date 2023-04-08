package com.mapofzones.statsextractor.services.core;

import com.mapofzones.statsextractor.data.repository.core.IBaseCoreRepository;
import com.mapofzones.statsextractor.utils.time.Interval;
import com.mapofzones.statsextractor.utils.time.IntervalCalculation;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BaseCoreService<R extends IBaseCoreRepository<E>, E> implements IBaseCoreService<R, E> {

    private final R coreRepository;
    private final LocalDateTime start;
    private final Integer period;

    public BaseCoreService(R coreRepository, LocalDateTime start, Integer period) {
        this.coreRepository = coreRepository;
        this.start = start;
        this.period = period;
    }

    public List<E> getDataList(LocalDateTime now) {

        List<E> dataList = new ArrayList<>();
        List<Interval> intervalList = IntervalCalculation.getIntervalList(start, now, period);

        log.info("Separated on {} intervals", intervalList.size());
        for (Interval interval : intervalList)
            dataList.addAll(coreRepository.read(interval.getStart(), interval.getEnd()));

        return dataList;
    }
}
