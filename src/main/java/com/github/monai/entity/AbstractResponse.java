package com.github.monai.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

class AbstractResponse {
  private final StatusCode status;

  public enum StatusCode {
    SUCCESS,
    ERROR
  }

  AbstractResponse(@JsonProperty("status") StatusCode status) {
    this.status = status;
  }
}
