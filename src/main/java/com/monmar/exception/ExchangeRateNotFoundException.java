package com.monmar.exception;

public class ExchangeRateNotFoundException extends RuntimeException {

    private String message;

    public ExchangeRateNotFoundException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage(){
        return message;
    }
}
