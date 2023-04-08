package com.mapofzones.statsextractor.services.core;

import com.mapofzones.statsextractor.config.props.CashflowProps;
import com.mapofzones.statsextractor.data.domain.Chart;
import com.mapofzones.statsextractor.data.repository.core.CashflowRepository;
import org.springframework.stereotype.Service;

@Service
public class CashflowService extends BaseCoreService<CashflowRepository, Chart> implements IBaseCoreService<CashflowRepository, Chart> {

    public CashflowService(CashflowRepository cashflowRepository, CashflowProps cashflowProps) {
        super(cashflowRepository, cashflowProps.getStart(), cashflowProps.getPeriod());
    }
}
