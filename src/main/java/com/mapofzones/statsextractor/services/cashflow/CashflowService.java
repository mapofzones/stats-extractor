package com.mapofzones.statsextractor.services.cashflow;

import com.mapofzones.statsextractor.config.props.CashflowProps;
import com.mapofzones.statsextractor.data.domain.Cashflow;
import com.mapofzones.statsextractor.data.repository.CashflowRepository;
import com.mapofzones.statsextractor.services.BaseService;
import com.mapofzones.statsextractor.services.IBaseService;
import org.springframework.stereotype.Service;

@Service
public class CashflowService extends BaseService<CashflowRepository, Cashflow> implements IBaseService<CashflowRepository, Cashflow> {

    public CashflowService(CashflowRepository cashflowRepository, CashflowProps cashflowProps) {
        super(cashflowRepository, cashflowProps.getStart(), cashflowProps.getPeriod());
    }
}
