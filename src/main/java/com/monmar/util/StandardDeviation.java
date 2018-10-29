package com.monmar.util;

import com.monmar.domain.Rate;

import java.util.List;

public class StandardDeviation {

    public static double getStandardDeviationOfPopulFromList(List<Rate> rates) {

        final double average = rates.stream().mapToDouble(Rate::getAsk).average().orElse(0.0);

        final double sumOfVariation = rates.stream().mapToDouble(Rate::getAsk).map(r -> Math.pow((r - average), 2)).sum();

        return Math.sqrt(sumOfVariation / rates.size());

    }
}
