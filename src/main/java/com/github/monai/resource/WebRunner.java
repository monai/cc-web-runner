package com.github.monai.resource;


import com.github.monai.VoidErrorManager;
import com.github.monai.entity.CompilerRequest;
import com.github.monai.entity.CompilerResponse;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.Result;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WebRunner {
  @POST
  @Path("/compile")
  public CompilerResponse compile(CompilerRequest request) {
    Compiler compiler = new Compiler(new VoidErrorManager());
    Result result = compiler.compile(request.externs, request.sources, request.options);
    String source = compiler.toSource();

    return new CompilerResponse(result, source);
  }
}
