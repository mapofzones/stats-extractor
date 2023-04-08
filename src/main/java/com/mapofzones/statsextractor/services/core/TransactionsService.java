package com.mapofzones.statsextractor.services.core;

import com.mapofzones.statsextractor.config.props.TransactionsProps;
import com.mapofzones.statsextractor.data.domain.Chart;
import com.mapofzones.statsextractor.data.repository.core.TransactionsRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionsService extends BaseCoreService<TransactionsRepository, Chart> implements IBaseCoreService<TransactionsRepository, Chart> {

    public TransactionsService(TransactionsRepository transactionsRepository, TransactionsProps transactionsProps) {
        super(transactionsRepository, transactionsProps.getStart(), transactionsProps.getPeriod());
    }
}
