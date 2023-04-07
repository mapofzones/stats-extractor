package com.mapofzones.statsextractor.schedulers;

import com.mapofzones.statsextractor.services.ExtractorFacade;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private final ExtractorFacade extractorFacade;

    public Scheduler(ExtractorFacade extractorFacade) {
        this.extractorFacade = extractorFacade;
    }

    @Scheduled(fixedDelayString = "${stats-extractor.sync-time}")
    public void run() {
        extractorFacade.update();
    }
}
