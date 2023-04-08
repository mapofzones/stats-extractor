package com.mapofzones.statsextractor.services.core;

import com.mapofzones.statsextractor.config.props.TransfersProps;
import com.mapofzones.statsextractor.data.domain.Chart;
import com.mapofzones.statsextractor.data.repository.core.TransfersRepository;
import org.springframework.stereotype.Service;

@Service
public class TransfersService extends BaseCoreService<TransfersRepository, Chart> implements IBaseCoreService<TransfersRepository, Chart> {

    public TransfersService(TransfersRepository repository, TransfersProps transfersProps) {
        super(repository, transfersProps.getStart(), transfersProps.getPeriod());
    }
}
