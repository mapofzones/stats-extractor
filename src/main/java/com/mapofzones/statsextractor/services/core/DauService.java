package com.mapofzones.statsextractor.services.core;

import com.mapofzones.statsextractor.config.props.DauProps;
import com.mapofzones.statsextractor.data.domain.Chart;
import com.mapofzones.statsextractor.data.repository.core.DauRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DauService extends BaseCoreService<DauRepository, Chart> implements IBaseCoreService<DauRepository, Chart> {

    public DauService(DauRepository dauRepository, DauProps dauProps) {
        super(dauRepository, dauProps.getStart(), dauProps.getPeriod());
    }
}
