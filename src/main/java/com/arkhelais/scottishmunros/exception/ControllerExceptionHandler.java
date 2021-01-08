package com.arkhelais.scottishmunros.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(value = {MunroException.class})
  public ResponseEntity<ErrorResponse> handleMunroException(MunroException exception) {
    return ResponseEntity
        .status(exception.getErrorType().getHttpStatus().value())
        .contentType(MediaType.APPLICATION_JSON)
        .body(exception.getErrorType().getErrorResponse());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleException(Exception exception) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .contentType(MediaType.APPLICATION_JSON)
        .body(exception.getLocalizedMessage());
  }

}