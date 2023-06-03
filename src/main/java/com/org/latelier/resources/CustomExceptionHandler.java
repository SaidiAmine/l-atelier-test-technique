package com.org.latelier.resources;

import com.org.latelier.exceptions.ApiErrorResponse;
import com.org.latelier.exceptions.EntityNotFoundException;
import com.org.latelier.exceptions.ExternalApiCallException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ExternalApiCallException.class)
    public ResponseEntity<Object> handleExternalApiCallException(ExternalApiCallException ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(apiErrorResponse, new HttpHeaders(), apiErrorResponse.getStatus());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(apiErrorResponse, new HttpHeaders(), apiErrorResponse.getStatus());
    }
}
