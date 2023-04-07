package com.mapofzones.statsextractor.utils.time;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IntervalCalculation {

    public static List<Interval> getIntervalList(LocalDateTime startDate, LocalDateTime now, int days) {
        List<Interval> intervals = new ArrayList<>();

        LocalDateTime start = startDate;
        LocalDateTime end = startDate;


        while (end.isBefore(now)) {
            end = start.plusDays(days);

            Interval interval = new Interval();
            interval.setStart(start);
            if (end.isBefore(now))
                interval.setEnd(end);
            else interval.setEnd(now);

            intervals.add(interval);

            start = end;
        }

//        do {
//            end = end.isBefore(now) ? start.plusDays(days) : now;
//
//            Interval interval = new Interval();
//            interval.setStart(start);
//            interval.setEnd(end);
//
//            intervals.add(interval);
//
//            start = end;
//
//        } while (end.isBefore(now));

        return intervals;
    }
}
