package com.github.monai.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.core.Response;

public class ErrorResponse extends AbstractResponse {
  private final String message;
  private final Throwable exception;

  public ErrorResponse(@JsonProperty("exception") Throwable ex,
                       boolean debug) {
    super(StatusCode.ERROR);

    String message = ex.getMessage();
    if (null != message) {
      this.message = message;
    } else {
      Response.Status statusInfo = Response.Status.INTERNAL_SERVER_ERROR;
      this.message = "HTTP " + statusInfo.getStatusCode() + ' ' + statusInfo.getReasonPhrase();
    }
    this.exception = debug ? ex : null;
  }
}
