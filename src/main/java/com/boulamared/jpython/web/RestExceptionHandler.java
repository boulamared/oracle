/**
 * 
 */
package com.boulamared.jpython.web;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * <h1>RestExceptionHandler</h1>
 * <p>
 * This class is used to handle the exceptions raised inside the Rest Controller
 * </p>
 * @author BOULAMMO
 *
 */
@Deprecated
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	/*
	 * (non-Javadoc)s
	 * 
	 * @see org.springframework.web.servlet.mvc.method.annotation.
	 * ResponseEntityExceptionHandler#handleHttpMessageNotReadable(org.
	 * springframework.http.converter.HttpMessageNotReadableException,
	 * org.springframework.http.HttpHeaders,
	 * org.springframework.http.HttpStatus,
	 * org.springframework.web.context.request.WebRequest)
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String errorMessage = "Interpreter";
		return super.handleHttpMessageNotReadable(ex, headers, status, request);
	}

}
