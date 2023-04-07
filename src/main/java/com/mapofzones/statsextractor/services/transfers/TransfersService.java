package com.mapofzones.statsextractor.services.transfers;

import com.mapofzones.statsextractor.config.props.TransfersProps;
import com.mapofzones.statsextractor.data.domain.Transfers;
import com.mapofzones.statsextractor.data.repository.TransfersRepository;
import com.mapofzones.statsextractor.services.BaseService;
import com.mapofzones.statsextractor.services.IBaseService;
import org.springframework.stereotype.Service;

@Service
public class TransfersService extends BaseService<TransfersRepository, Transfers> implements IBaseService<TransfersRepository, Transfers> {

    public TransfersService(TransfersRepository repository, TransfersProps transfersProps) {
        super(repository, transfersProps.getStart(), transfersProps.getPeriod());
    }
}
