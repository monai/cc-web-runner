package com.github.monai.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AbstractResponse {
  public final StatusCode status;

  public enum StatusCode {
    SUCCESS,
    ERROR
  }

  public AbstractResponse(@JsonProperty("status") StatusCode status) {
    this.status = status;
  }
}
