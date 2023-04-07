package com.mapofzones.statsextractor.services;

import com.mapofzones.statsextractor.data.domain.Cashflow;
import com.mapofzones.statsextractor.data.repository.core.CashflowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ExtractorFacade {

    IService<CashflowRepository, Cashflow> cashflowService;

    public ExtractorFacade(@Qualifier("cashflowService") IService<CashflowRepository, Cashflow> cashflowService) {
        this.cashflowService = cashflowService;
    }

    @Transactional
    public void update() {

        LocalDateTime now = LocalDateTime.now();

        // find
        log.info("Start finding data");
        List<Cashflow> cashflowList = cashflowService.getDataList(now);
        log.info("Finish finding data");


        // delete
        log.info("Start removing data");
        cashflowService.deleteData();
        log.info("Finish finding data");

        // write
        log.info("Start writing data");
        cashflowService.writeData(cashflowList);
        log.info("Finish writing data");
    }
}
