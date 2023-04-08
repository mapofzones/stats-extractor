package com.mapofzones.statsextractor.services.api;

import com.mapofzones.statsextractor.data.domain.Chart;
import com.mapofzones.statsextractor.data.repository.api.ChartRepository;
import org.springframework.stereotype.Service;

@Service
public class ChartService extends BaseApiService<ChartRepository, Chart> implements IBaseApiService<ChartRepository, Chart> {

    public ChartService(ChartRepository apiRepository) {
        super(apiRepository);
    }
}
