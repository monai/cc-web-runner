package com.github.monai.resource;


import com.github.monai.Application;
import com.github.monai.entity.CompilerRequest;
import com.github.monai.entity.CompilerResponse;
import com.github.monai.entity.OptionsRequest;
import com.google.javascript.jscomp.*;
import com.google.javascript.jscomp.Compiler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashMap;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WebRunner {
  @GET
  @Path("/status")
  public HashMap<String, Object> status() {
    HashMap<String, Object> out = new HashMap<>();

    out.put("compilerVersion", Compiler.getReleaseVersion());

    return out;
  }

  @POST
  @Path("/options")
  public HashMap<String, Object> options(OptionsRequest request) {
    HashMap<String, Object> out = new HashMap<>();

    if (null == request) {
      out.put("options", new CompilerOptions());
      return out;
    }

    request.compilationLevelOptions.setOptions(request.options);
    request.warningLevels.setOptions(request.options);

    out.put("options", request.options);

    return out;
  }

  @GET
  @Path("/externs")
  public HashMap<String, Object> externs() throws IOException {
    HashMap<String, Object> out = new HashMap<>();

    CompilerOptions.Environment environment = new CompilerOptions().getEnvironment();
    out.put("externs", CommandLineRunner.getBuiltinExterns(environment));

    return out;
  }

  @POST
  @Path("/compile")
  public CompilerResponse compile(CompilerRequest request) {
    if (null != request.compilationLevelOptions.compilationLevel) {
      if (CompilationLevel.ADVANCED_OPTIMIZATIONS == request.compilationLevelOptions.compilationLevel) {
        CompilerOptions.Environment env = request.options.getEnvironment();
        request.externs.addAll(Application.defaultExterns.externs.get(env));
      }

      request.compilationLevelOptions.setOptions(request.options);
      request.warningLevels.setOptions(request.options);
    }

    Compiler compiler = new Compiler();
    Result result = compiler.compile(request.externs, request.sources, request.options);
    String source = compiler.toSource();

    return new CompilerResponse(result, source);
  }
}
