package com.mapofzones.statsextractor.data.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"blockchain", "networkId", "chartType", "pointIndex"})
public class Chart {

    private String blockchain;
    private String networkId;
    private String chartType;
    private Long pointIndex;
    private BigDecimal pointValue;

}
