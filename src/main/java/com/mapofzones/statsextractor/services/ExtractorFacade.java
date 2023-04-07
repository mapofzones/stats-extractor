package com.mapofzones.statsextractor.services;

import com.mapofzones.statsextractor.data.domain.Cashflow;
import com.mapofzones.statsextractor.data.domain.Transfers;
import com.mapofzones.statsextractor.data.repository.CashflowRepository;
import com.mapofzones.statsextractor.data.repository.TransfersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ExtractorFacade {

    IBaseService<CashflowRepository, Cashflow> cashflowService;
    IBaseService<TransfersRepository, Transfers> transfersService;

    public ExtractorFacade(@Qualifier("cashflowService") IBaseService<CashflowRepository, Cashflow> cashflowService,
                           @Qualifier("transfersService") IBaseService<TransfersRepository, Transfers> transfersService) {
        this.cashflowService = cashflowService;
        this.transfersService = transfersService;
    }

    @Transactional
    public void update() {

        LocalDateTime now = LocalDateTime.now();

        // find
        log.info("Start finding cashflow data");
        List<Cashflow> cashflowList = cashflowService.getDataList(now);
        log.info("Finish finding cashflow data");
        log.info("Start finding transfers data");
        List<Transfers> transfersList = transfersService.getDataList(now);
        log.info("Finish finding transfers data");

        // delete
        log.info("Start removing data");
        cashflowService.deleteData();
        log.info("Finish finding data");

        // write
        log.info("Start writing cashflow data");
        cashflowService.writeData(cashflowList);
        log.info("Finish writing cashflow data");
        log.info("Start writing transfers data");
        transfersService.writeData(transfersList);
        log.info("Finish finding transfers data");
    }
}
