package com.monmar;

import com.monmar.dao.ExchangeRateClient;
import com.monmar.domain.Rate;
import com.monmar.exception.ExchangeRateNotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExchangeRateClientTest {

    ExchangeRateClient exchangeRateClient;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Before
    public void setUp() {
        exchangeRateClient = new ExchangeRateClient();
    }

    @Test
    public void shouldReturnFiveRatesForEuro(){
        final String currencyCode = "EUR";
        final String dateFrom = "2017-11-20";
        final String dateTo = "2017-11-24";

        assertEquals(5, exchangeRateClient.getExchangeRateByCurrencyAndDate(currencyCode,dateFrom,dateTo).getRates().size());

    }

    @Test
    public void shouldReturnAskPriceStandardDeviation(){
        final String currencyCode = "EUR";
        final String dateFrom = "2017-11-20";
        final String dateTo = "2017-11-24";
        List<Rate> rates = exchangeRateClient.getExchangeRateByCurrencyAndDate(currencyCode,dateFrom,dateTo).getRates();

        assertEquals(0.0101, exchangeRateClient.getAskPriceStandardDeviationFromList(rates), 0.0001);
    }

    @Test
    public void shouldReturnBidPriceAverage(){
        final String currencyCode = "EUR";
        final String dateFrom = "2017-11-20";
        final String dateTo = "2017-11-24";
        List<Rate> rates = exchangeRateClient.getExchangeRateByCurrencyAndDate(currencyCode,dateFrom,dateTo).getRates();

        assertEquals(4.1815, exchangeRateClient.getBidPriceAverageFromList(rates),0.0001);
    }

    @Test
    public void shouldHandleFailure() {
        expectedException.expect(ExchangeRateNotFoundException.class);
        expectedException.expectMessage("No Exchange rate for given parameters.");

        final String notExistingCurrencyCode = "TMP";
        final String dateFrom = "2017-11-20";
        final String dateTo = "2017-11-24";
        List<Rate> rates = exchangeRateClient.getExchangeRateByCurrencyAndDate(notExistingCurrencyCode, dateFrom, dateTo).getRates();
        exchangeRateClient.getBidPriceAverageFromList(rates);
    }
}
