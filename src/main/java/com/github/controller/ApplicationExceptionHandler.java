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
		log.error("Request url: {}", req.getRequestURL(), ex);
		var errorMessageResponse = new ErrorMessageResponse("User not found", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorMessageResponse>(errorMessageResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<ErrorMessageResponse> missingHeaderException(HttpServletRequest req, MissingRequestHeaderException ex) {
		log.error("Request url: {}", req.getRequestURL(), ex);
		var errorMessageResponse = new ErrorMessageResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ErrorMessageResponse>(errorMessageResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ResponseEntity<ErrorMessageResponse> notAcceptedMediaType(HttpServletRequest req, HttpMediaTypeNotAcceptableException ex) {
		log.error("Request url: {}", req.getRequestURL(), ex);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		var errorMessageResponse = new ErrorMessageResponse(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE.value());
		return 	ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
							  .headers(headers)
							  .body(errorMessageResponse);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorMessageResponse> constraintViolation(HttpServletRequest req, ConstraintViolationException ex) {
		log.error("Request url: {}", req.getRequestURL(), ex);
		var errorMessageResponse = new ErrorMessageResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ErrorMessageResponse>(errorMessageResponse,HttpStatus.BAD_REQUEST);
	}
}
