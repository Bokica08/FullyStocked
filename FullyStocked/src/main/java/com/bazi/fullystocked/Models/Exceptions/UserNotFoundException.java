package com.bazi.fullystocked.Models.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String username) {
        super(String.format("User with username: %s was not found", username));
    }
}
