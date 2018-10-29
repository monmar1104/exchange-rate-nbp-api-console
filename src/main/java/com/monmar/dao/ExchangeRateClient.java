package com.monmar.dao;

import com.monmar.domain.ExchangeRateResponse;
import com.monmar.domain.Rate;
import com.monmar.exception.ExchangeRateBadRequestException;
import com.monmar.exception.ExchangeRateNotFoundException;
import com.monmar.util.StandardDeviation;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.Response;
import java.util.List;

public class ExchangeRateClient {

    private final String API_URL = "https://api.nbp.pl/api/exchangerates/rates/C/";

    public ExchangeRateResponse getExchangeRateByCurrencyAndDate(String code, String dateFrom, String dateTo) {

        ResteasyClient client = new ResteasyClientBuilder().build();

        ResteasyWebTarget target = client.target(API_URL + code + "/" + dateFrom + "/" + dateTo);

        Response response = target.request().get();

        if (response.getStatus() == 404) {
            throw new ExchangeRateNotFoundException("No Exchange rate for given parameters.");
        } else if (response.getStatus() == 400) {
            throw new ExchangeRateBadRequestException("Bad request");
        } else {
            ExchangeRateResponse exchangeRateResponse = response.readEntity(ExchangeRateResponse.class);
            response.close();

            return exchangeRateResponse;
        }
    }

    public double getBidPriceAverageFromList(List<Rate> rates) {
        return rates.stream().mapToDouble(Rate::getBid).average().orElse(0.0);
    }

    public double getAskPriceStandardDeviationFromList(List<Rate> rates) {
        return StandardDeviation.getStandardDeviationOfPopulFromList(rates);
    }

}
