package com.quicksilvarad.springbootrestfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;
//Custom class for handling the unique email constraint against every user
@ResponseStatus(HttpStatus.CONFLICT)
public class EmailIDAlreadyExistsException extends RuntimeException {

    private String message;

    public EmailIDAlreadyExistsException(String message)
    {
        super(message);

    }


}
