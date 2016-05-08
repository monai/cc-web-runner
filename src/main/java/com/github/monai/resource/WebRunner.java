package com.github.monai.resource;


import com.github.monai.Application;
import com.github.monai.entity.CompilerRequest;
import com.github.monai.entity.CompilerResponse;
import com.github.monai.entity.Optimizations;
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

  @GET
  @Path("/options")
  public CompilerOptions options(@QueryParam("level") CompilationLevel level,
                                        @QueryParam("debug") @DefaultValue("false") boolean debug,
                                        @QueryParam("typeBased") @DefaultValue("false") boolean typeBased,
                                        @QueryParam("wrappedOutput") @DefaultValue("false") boolean wrappedOutput) throws IOException {
    CompilerOptions options = new CompilerOptions();
    if (null != level) {
      Optimizations optim = new Optimizations(level, debug, typeBased, wrappedOutput);
      applyOptimizations(optim, options);
    }

    return options;
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
  public CompilerResponse compile(CompilerRequest request) throws IOException {
    Optimizations optim = request.optimizations;

    if (null != optim && null != optim.level) {
      if (CompilationLevel.ADVANCED_OPTIMIZATIONS == optim.level) {
        CompilerOptions.Environment env = request.options.getEnvironment();
        request.externs.addAll(Application.defaultExterns.externs.get(env));
      }

      applyOptimizations(optim, request.options);
    }

    Compiler compiler = new Compiler(new VoidErrorManager());
    Result result = compiler.compile(request.externs, request.sources, request.options);
    String source = compiler.toSource();

    return new CompilerResponse(result, source);
  }

  private void applyOptimizations(Optimizations optim, CompilerOptions options) {
    optim.level.setOptionsForCompilationLevel(options);

    if (optim.debug) {
      optim.level.setDebugOptionsForCompilationLevel(options);
    }

    if (optim.typeBased) {
      optim.level.setTypeBasedOptimizationOptions(options);
    }

    if (optim.wrappedOutput) {
      optim.level.setWrappedOutputOptimizations(options);
    }
  }

  private class VoidErrorManager extends BasicErrorManager {
    @Override
    public void println(CheckLevel level, JSError error) {}

    @Override
    protected void printSummary() {}
  }
}
