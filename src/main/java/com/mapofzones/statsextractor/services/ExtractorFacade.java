package com.mapofzones.statsextractor.services;

import com.mapofzones.statsextractor.data.domain.Chart;
import com.mapofzones.statsextractor.data.repository.api.ChartRepository;
import com.mapofzones.statsextractor.data.repository.core.CashflowRepository;
import com.mapofzones.statsextractor.data.repository.core.DauRepository;
import com.mapofzones.statsextractor.data.repository.core.MauRepository;
import com.mapofzones.statsextractor.data.repository.core.TransactionsRepository;
import com.mapofzones.statsextractor.data.repository.core.TransfersRepository;
import com.mapofzones.statsextractor.services.api.IBaseApiService;
import com.mapofzones.statsextractor.services.core.IBaseCoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ExtractorFacade {

    IBaseApiService<ChartRepository, Chart> chartService;
    IBaseCoreService<CashflowRepository, Chart> cashflowService;
    IBaseCoreService<TransfersRepository, Chart> transfersService;
    IBaseCoreService<MauRepository, Chart> mauService;
    IBaseCoreService<DauRepository, Chart> dauService;
    IBaseCoreService<TransactionsRepository, Chart> transactionsService;


    public ExtractorFacade(@Qualifier("chartService") IBaseApiService<ChartRepository, Chart> chartService,
                           @Qualifier("cashflowService") IBaseCoreService<CashflowRepository, Chart> cashflowService,
                           @Qualifier("transfersService") IBaseCoreService<TransfersRepository, Chart> transfersService,
                           @Qualifier("mauService") IBaseCoreService<MauRepository, Chart> mauService,
                           @Qualifier("dauService") IBaseCoreService<DauRepository, Chart> dauService,
                           @Qualifier("transactionsService") IBaseCoreService<TransactionsRepository, Chart> transactionsService

    ) {
        this.chartService = chartService;
        this.cashflowService = cashflowService;
        this.transfersService = transfersService;
        this.mauService = mauService;
        this.dauService = dauService;
        this.transactionsService = transactionsService;
    }

    @Transactional
    public void update() {

        LocalDateTime now = LocalDateTime.now();

        // find
        log.info("Start finding cashflow data");
        List<Chart> cashflowList = cashflowService.getDataList(now);
        log.info("Finish finding cashflow data");

        log.info("Start finding transfers data");
        List<Chart> transfersList = transfersService.getDataList(now);
        log.info("Finish finding transfers data");

        log.info("Start finding mau data");
        List<Chart> mauList = mauService.getDataList(now);
        log.info("Finish finding mau data");

        log.info("Start finding dau data");
        List<Chart> dauList = dauService.getDataList(now);
        log.info("Finish finding dau data");

        log.info("Start finding transactions data");
        List<Chart> transactionsList = transactionsService.getDataList(now);
        log.info("Finish finding transactions data");

        // delete
        log.info("Start removing data");
        chartService.deleteData();
        log.info("Finish removing data");

        // write
        log.info("Start writing cashflow data");
        chartService.writeData(cashflowList);
        log.info("Finish writing cashflow data");

        log.info("Start writing transfers data");
        chartService.writeData(transfersList);
        log.info("Finish writing transfers data");

        log.info("Start writing mau data");
        chartService.writeData(mauList);
        log.info("Finish writing transfers data");

        log.info("Start writing dau data");
        chartService.writeData(dauList);
        log.info("Finish writing dau data");

        log.info("Start writing transactions data");
        chartService.writeData(transactionsList);
        log.info("Finish writing transactions data");

    }
}
