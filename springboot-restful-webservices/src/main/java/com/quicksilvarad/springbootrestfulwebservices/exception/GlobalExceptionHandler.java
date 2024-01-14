package com.quicksilvarad.springbootrestfulwebservices.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class) //specific class for handling exception
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage() , webRequest.getDescription(false), "USER_NOT_FOUND" );
        System.out.println(exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailIDAlreadyExistsException.class) //specific class for handling exception
    public ResponseEntity<ErrorDetails> handleEmailAlreadyExistsException(EmailIDAlreadyExistsException exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage() , webRequest.getDescription(false), "EMAIL_ALREADY_EXISTS" );
        System.out.println(exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(Exception.class) //specific class for handling exception
    public ResponseEntity<ErrorDetails> handleAllException(Exception exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage() , webRequest.getDescription(false), "LOOK_FOR_ERRORS_IN_REQUEST" );
        System.out.println(exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.I_AM_A_TEAPOT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        List<ObjectError> objectErrorList=ex.getBindingResult().getAllErrors(); //Object Error class for listing all the errors
        objectErrorList.forEach((error)->
        {
            String fieldName = ((FieldError)error).getField();//typecasting to get the fieldName
            String message = error.getDefaultMessage(); // to get the message
            errors.put(fieldName,message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

