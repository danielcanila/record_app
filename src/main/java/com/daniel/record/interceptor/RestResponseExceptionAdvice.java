package com.daniel.record.interceptor;

import com.daniel.record.exception.HttpBadRequestException;
import com.daniel.record.exception.HttpUnauthorizedException;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionAdvice extends ResponseEntityExceptionHandler {

	@ResponseBody
	@ExceptionHandler(HttpUnauthorizedException.class)
	protected ResponseEntity<String> handleUnauthorized(
			HttpUnauthorizedException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
	}

	@ResponseBody
	@ExceptionHandler(HttpBadRequestException.class)
	protected ResponseEntity<String> handleBadRequest(HttpBadRequestException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<String> handleInternalServerError(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException exception,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {
		var errorMessages = exception.getBindingResult().getFieldErrors()
				                    .stream()
				                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
				                    .collect(Collectors.toList());
		return new ResponseEntity(errorMessages, HttpStatus.BAD_REQUEST);
	}
}
