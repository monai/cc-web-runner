package com.github.monai.entity;

public class AbstractResponse {
  public final StatusCode status;

  public enum StatusCode {
    SUCCESS,
    ERROR
  }

  public AbstractResponse(StatusCode status) {
    this.status = status;
  }
}
