package com.github.monai.entity;

public class ErrorResponse extends AbstractResponse {
  public final String message;
  public final Throwable exception;

  public ErrorResponse(Throwable exception) {
    super(StatusCode.ERROR);
    this.message = exception.getMessage();
    this.exception = exception;
  }
}
