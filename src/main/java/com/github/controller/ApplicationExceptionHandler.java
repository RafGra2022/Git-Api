package com.github.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @ExceptionHandler(UserPrincipalNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> userNotFoundException(HttpServletRequest req, UserPrincipalNotFoundException ex) {
        return createErrorMessageResponse(
                req.getRequestURL().toString(),
                HttpStatus.NOT_FOUND,
                ex);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorMessageResponse> missingHeaderException(HttpServletRequest req, MissingRequestHeaderException ex) {
        return createErrorMessageResponse(
                req.getRequestURL().toString(),
                HttpStatus.BAD_REQUEST,
                ex);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<ErrorMessageResponse> notAcceptedMediaType(HttpServletRequest req, HttpMediaTypeNotAcceptableException ex) {
        return createErrorMessageResponse(
                req.getRequestURL().toString(),
                HttpStatus.NOT_ACCEPTABLE,
                ex);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessageResponse> constraintViolation(HttpServletRequest req, ConstraintViolationException ex) {
        return createErrorMessageResponse(
                req.getRequestURL().toString(),
                HttpStatus.BAD_REQUEST,
                ex);
    }

    private static ResponseEntity<ErrorMessageResponse> createErrorMessageResponse(String url, HttpStatus httpStatus, Exception ex) {
        log.error("Request url: {}", url, ex);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(ex.getMessage(), httpStatus.value());
        return ResponseEntity
                .status(httpStatus)
                .headers(headers)
                .body(errorMessageResponse);
    }
}
