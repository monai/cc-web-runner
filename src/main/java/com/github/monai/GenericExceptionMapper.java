package com.github.monai;

import com.github.monai.entity.ErrorResponse;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;

@Produces(MediaType.APPLICATION_JSON)
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
  @Context
  UriInfo ui;

  @Override
  public Response toResponse(Throwable ex) {
    return Response.status(getStatus(ex))
            .entity(new ErrorResponse(ex, ui.getQueryParameters().containsKey("debug")))
            .type(MediaType.APPLICATION_JSON)
            .build();
  }

  private int getStatus(Throwable ex) {
    if (ex instanceof ClientErrorException) {
      return ((ClientErrorException)ex).getResponse().getStatus();
    } else {
      return 500;
    }
  }
}
