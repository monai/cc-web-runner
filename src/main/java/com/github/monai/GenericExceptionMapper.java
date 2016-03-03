package com.github.monai;

import com.github.monai.entity.ErrorResponse;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

@Produces(MediaType.APPLICATION_JSON)
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

  @Override
  public Response toResponse(Throwable ex) {
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(new ErrorResponse(ex))
            .build();
  }
}
