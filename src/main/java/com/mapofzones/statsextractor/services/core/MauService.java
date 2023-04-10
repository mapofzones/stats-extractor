package com.mapofzones.statsextractor.services.core;

import com.mapofzones.statsextractor.config.props.MauProps;
import com.mapofzones.statsextractor.data.domain.Chart;
import com.mapofzones.statsextractor.data.repository.core.MauRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MauService extends BaseCoreService<MauRepository, Chart> implements IBaseCoreService<MauRepository, Chart> {

    public MauService(MauRepository mauRepository, MauProps mauProps) {
        super(mauRepository, mauProps.getStart(), mauProps.getPeriod());
    }
}
