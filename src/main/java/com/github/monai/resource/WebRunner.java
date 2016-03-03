package com.github.monai.resource;


import com.github.monai.VoidErrorManager;
import com.github.monai.entity.CompilerRequest;
import com.github.monai.entity.CompilerResponse;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.Result;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WebRunner {
  @Context
  UriInfo uriInfo;

  @GET
  @Path("/")
  public Response index() {
    URI uri = uriInfo.getAbsolutePathBuilder().path("compile").build();
    return Response.seeOther(uri).build();
  }

  @GET
  @Path("compile")
  public Response compile() {
    return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
  }

  @POST
  @Path("/compile")
  public CompilerResponse compile(CompilerRequest request) {
    Compiler compiler = new Compiler(new VoidErrorManager());
    Result result = compiler.compile(request.externs, request.sources, request.options);
    String source = compiler.toSource();

    return new CompilerResponse(result, source);
  }
}
