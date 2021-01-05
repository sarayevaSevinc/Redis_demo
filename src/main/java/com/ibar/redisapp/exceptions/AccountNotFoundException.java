package com.ibar.redisapp.exceptions;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String str) {
        super(str);
    }

}