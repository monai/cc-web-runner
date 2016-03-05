package com.github.monai.resource;


import com.github.monai.entity.CompilerRequest;
import com.github.monai.entity.CompilerResponse;
import com.google.javascript.jscomp.*;
import com.google.javascript.jscomp.Compiler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WebRunner {
  @GET
  @Path("/status")
  public HashMap<String, Object> status() {
    HashMap<String, Object> out = new HashMap<>();

    out.put("options", new CompilerOptions());
    out.put("compilerVersion", Compiler.getReleaseVersion());

    return out;
  }

  @POST
  @Path("/compile")
  public CompilerResponse compile(CompilerRequest request) {
    Compiler compiler = new Compiler(new VoidErrorManager());
    Result result = compiler.compile(request.externs, request.sources, request.options);
    String source = compiler.toSource();

    return new CompilerResponse(result, source);
  }

  class VoidErrorManager extends BasicErrorManager {
    @Override
    public void println(CheckLevel level, JSError error) {}

    @Override
    protected void printSummary() {}
  }
}
