package com.mapofzones.statsextractor.utils.time;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IntervalCalculation {

    public static List<Interval> getIntervalList(LocalDateTime startDate, LocalDateTime now, int month) {
        List<Interval> intervals = new ArrayList<>();

        LocalDateTime start = startDate;
        LocalDateTime end = startDate;

        while (end.isBefore(now)) {

            end = start.plusMonths(month).minusNanos(1);

            Interval interval = new Interval();
            interval.setStart(start);
            if (end.isBefore(now))
                interval.setEnd(end);
            else interval.setEnd(now);

            intervals.add(interval);
            start = end.plusNanos(1);
        }
        return intervals;
    }
}
