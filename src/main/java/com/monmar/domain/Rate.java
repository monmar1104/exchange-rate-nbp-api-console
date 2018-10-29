package com.monmar.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rate {

    private String no;
//    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private String effectiveDate;
    private double bid;
    private double ask;
}
