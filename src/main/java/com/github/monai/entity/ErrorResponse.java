package com.github.monai.entity;

import com.owlike.genson.annotation.JsonProperty;

import javax.ws.rs.core.Response;

public class ErrorResponse extends AbstractResponse {
  public final String message;
  public final Throwable exception;

  public ErrorResponse(@JsonProperty("exception") Throwable ex,
                       boolean debug) {
    super(StatusCode.ERROR);

    String message = ex.getMessage();
    this.message = (null != message)
            ? message
            : Response.Status.INTERNAL_SERVER_ERROR.toString();
    this.exception = debug ? ex : null;
  }
}
