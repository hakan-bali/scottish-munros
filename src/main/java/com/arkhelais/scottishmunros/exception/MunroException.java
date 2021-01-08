package com.arkhelais.scottishmunros.exception;

import lombok.Getter;

@Getter
public class MunroException extends RuntimeException {

  private final ErrorType errorType;

  public MunroException(ErrorType errorType) {
    super(errorType.getDescription());
    this.errorType = errorType;
  }

}