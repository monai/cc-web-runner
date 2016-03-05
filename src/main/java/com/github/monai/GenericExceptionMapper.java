package com.github.monai;

import com.github.monai.entity.ErrorResponse;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;

import static javax.ws.rs.core.Response.Status;
import static javax.ws.rs.core.Response.status;

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
  @Context
  UriInfo ui;

  @Override
  public Response toResponse(Throwable ex) {
    return status(getStatus(ex))
            .entity(new ErrorResponse(ex, ui.getQueryParameters().containsKey("debug")))
            .type(MediaType.APPLICATION_JSON)
            .build();
  }

  private int getStatus(Throwable ex) {
    if (ex instanceof ClientErrorException) {
      return ((ClientErrorException)ex).getResponse().getStatus();
    } else {
      return Status.INTERNAL_SERVER_ERROR.getStatusCode();
    }
  }
}
