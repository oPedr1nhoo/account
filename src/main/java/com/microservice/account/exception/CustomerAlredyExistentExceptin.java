package com.microservice.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlredyExistentExceptin extends RuntimeException {

    public CustomerAlredyExistentExceptin(String message)
    {
        super(message);
    }

}
