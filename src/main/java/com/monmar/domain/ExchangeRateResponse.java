package com.monmar.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExchangeRateResponse {

    private String table;
    private String currency;
    private String code;
    private List<Rate> rates;
}
