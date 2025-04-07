package com.andev03.hackathon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountAlreadyExist extends RuntimeException {
    public AccountAlreadyExist(String message) {
        super(message);
    }
}
