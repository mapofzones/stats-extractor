package com.mapofzones.statsextractor.config.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "stats-extractor.queries.mau")
public class MauProps {

    LocalDateTime start;
    Integer period;

}
