package com.arkhelais.scottishmunros.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorType {

  CATEGORY_PARAMETER_INVALID(
      "1001", BAD_REQUEST, "[Category] Parameter Invalid"),
  MIN_HEIGHT_FORMAT_INVALID(
      "1002", BAD_REQUEST, "Number Parameter Format Invalid for [Min Height] Parameter"),
  MAX_HEIGHT_FORMAT_INVALID(
      "1003", BAD_REQUEST, "Number Parameter Format Invalid for [Max Height] Parameter"),
  HEIGHT_ORDER_ERROR(
      "1004", BAD_REQUEST, "[Minimum Height] Parameter Should Be Less Than [Maximum Height] Parameter"),
  LIMIT_MIN_VALUE_ERROR(
      "1005", BAD_REQUEST, "[Limit] Parameter Should Be A Positive Number"),
  LIMIT_FORMAT_INVALID(
      "1006", BAD_REQUEST, "Number Parameter Format Invalid for [Limit] Parameter"),
  SORT_PARAMETER_INVALID(
      "1007", BAD_REQUEST, "[SortBy] Parameter Invalid");

  private final String code;
  private final HttpStatus httpStatus;
  private final String description;

  public MunroException createException() {
    return new MunroException(this);
  }

  public ErrorResponse getErrorResponse() {
    return ErrorResponse.builder()
        .errorCode(getCode())
        .description(getDescription())
        .build();
  }

}