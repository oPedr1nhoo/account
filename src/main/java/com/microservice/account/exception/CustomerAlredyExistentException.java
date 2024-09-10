package com.microservice.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlredyExistentException extends RuntimeException {

    public CustomerAlredyExistentException(String message)
    {
        super(message);
    }

}
