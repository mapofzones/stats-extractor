package com.mapofzones.statsextractor.services.api;

import com.mapofzones.statsextractor.data.domain.Chart;
import com.mapofzones.statsextractor.data.repository.api.ChartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChartService extends BaseApiService<ChartRepository, Chart> implements IBaseApiService<ChartRepository, Chart> {

    public ChartService(ChartRepository apiRepository) {
        super(apiRepository);
    }
}
