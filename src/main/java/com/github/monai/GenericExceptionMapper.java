package com.github.monai;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.monai.entity.ErrorResponse;
import org.tinylog.Logger;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status;
import static javax.ws.rs.core.Response.status;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<JsonMappingException> {
  @Context
  UriInfo ui;

  @Override
  public Response toResponse(JsonMappingException ex) {
    Logger.error(ex.getCause());
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
