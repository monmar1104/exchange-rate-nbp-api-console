package com.monmar;

import com.monmar.dao.ExchangeRateClient;
import com.monmar.domain.ExchangeRateResponse;
import com.monmar.util.DateMatcher;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";

        ExchangeRateClient exchangeRateClient = new ExchangeRateClient();
        String currencyCode = null;
        String dateFrom = null;
        String dateTo = null;

        Scanner scanner = new Scanner(System.in);

        boolean notMatches = true;

        while (notMatches) {

            System.out.print("Podaj kod waluty (np. EUR): ");
            currencyCode = scanner.next().toUpperCase();
            if(currencyCode.length() != 3){
                System.out.println(ANSI_RED + "Podałeś niewłaściwy format kodu waluty!!! \n" + ANSI_RESET);
                continue;
            }

            System.out.print("Podaj datę początkową (RRRR-MM-DD): ");
            dateFrom = scanner.next();
            if (!DateMatcher.matches(dateFrom)) {
                System.out.println(ANSI_RED + "Podałeś niewłaściwy format daty!!! \n" + ANSI_RESET);
                continue;
            }

            System.out.print("Podaj datę końcową (RRRR-MM-DD): ");
            dateTo = scanner.next();
            if (!DateMatcher.matches(dateTo)) {
                System.out.println(ANSI_RED + "Podałeś niewłaściwy format daty!!! \n"+ ANSI_RESET);
                continue;
            }
            notMatches  = false;
        }

        ExchangeRateResponse rateResponse = exchangeRateClient.getExchangeRateByCurrencyAndDate(currencyCode, dateFrom, dateTo);
        Double stdVar = exchangeRateClient.getAskPriceStandardDeviationFromList(rateResponse.getRates());
        Double average = exchangeRateClient.getBidPriceAverageFromList(rateResponse.getRates());

        System.out.printf("Odchylenie standardowe kursów sprzedaży waluty " + currencyCode + " w okresie od " + dateFrom + " do " + dateTo + " wynosi: "+ ANSI_GREEN + "%.4f %n" , stdVar);
        System.out.printf(ANSI_RESET + "Średni kurs kupna waluty " + currencyCode + " w okresie od " + dateFrom + " do " + dateTo + " wynosi: "+ ANSI_GREEN + "%.4f %n", average);

    }
}
