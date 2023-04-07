package com.mapofzones.statsextractor.services.cashflow;

import com.mapofzones.statsextractor.config.props.CashflowProps;
import com.mapofzones.statsextractor.data.domain.Cashflow;
import com.mapofzones.statsextractor.data.repository.core.CashflowRepository;
import com.mapofzones.statsextractor.services.BaseService;
import org.springframework.stereotype.Service;

@Service
public class CashflowService extends BaseService<CashflowRepository, Cashflow> {

    public CashflowService(CashflowRepository cashflowRepository, CashflowProps cashflowProps) {
        super(cashflowRepository, cashflowProps.getStart(), cashflowProps.getPeriod());
    }
}
