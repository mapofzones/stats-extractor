package com.mapofzones.statsextractor.config.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.LocalDateTime;

@Getter
@Setter
@ConfigurationProperties(prefix = "stats-extractor.queries.transfers")
public class TransfersProps {

    LocalDateTime start;
    Integer period;

}
