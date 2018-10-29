package com.monmar.exception;

public class ExchangeRateBadRequestException extends RuntimeException {

    private String message;

    public ExchangeRateBadRequestException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage(){
        return message;
    }
}
