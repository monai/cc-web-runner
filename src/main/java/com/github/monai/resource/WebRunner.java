package com.github.monai.resource;


import com.github.monai.entity.CompilerRequest;
import com.github.monai.entity.CompilerResponse;
import com.google.javascript.jscomp.*;
import com.google.javascript.jscomp.Compiler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WebRunner {
  @GET
  @Path("/status")
  public HashMap<String, Object> status() throws IOException {
    HashMap<String, Object> out = new HashMap<>();

    CompilerOptions options = new CompilerOptions();

    out.put("externs", CommandLineRunner.getBuiltinExterns(options));
    out.put("options", options);
    out.put("compilerVersion", Compiler.getReleaseVersion());

    return out;
  }

  @POST
  @Path("/compile")
  public CompilerResponse compile(CompilerRequest request) throws IOException {
    List<SourceFile> externs = CommandLineRunner.getBuiltinExterns(request.options);
    externs.addAll(request.externs);

    Compiler compiler = new Compiler(new VoidErrorManager());
    Result result = compiler.compile(externs, request.sources, request.options);
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
