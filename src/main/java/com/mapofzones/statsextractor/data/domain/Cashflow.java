package com.mapofzones.statsextractor.data.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Cashflow {

    private String blockchain;
    private String networkId;
    private String chartType;
    private Long pointIndex;
    private BigDecimal pointValue;

}
