package com.github.monai.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.javascript.jscomp.CompilationLevel;
import com.google.javascript.jscomp.CompilerOptions;

public class CompilationLevelOptions {
  public final CompilationLevel compilationLevel;
  public final Boolean debug;
  public final Boolean useTypesForOptimization;
  public final Boolean assumeFunctionWrapper;

  public CompilationLevelOptions() {
    this.compilationLevel = CompilationLevel.fromString("SIMPLE");
    this.debug = false;
    this.useTypesForOptimization = false;
    this.assumeFunctionWrapper = false;
  }


  public CompilationLevelOptions(@JsonProperty("compilationLevel") CompilationLevel compilationLevel,
                                 @JsonProperty("debug") Boolean debug,
                                 @JsonProperty("useTypesForOptimization") Boolean useTypesForOptimization,
                                 @JsonProperty("assumeFunctionWrapper") Boolean assumeFunctionWrapper) {
    this.compilationLevel = null != compilationLevel ? compilationLevel : CompilationLevel.fromString("SIMPLE");
    this.debug = null != debug ? debug : false;
    this.useTypesForOptimization = null != useTypesForOptimization ? useTypesForOptimization : false;
    this.assumeFunctionWrapper = null != assumeFunctionWrapper ? assumeFunctionWrapper : false;
  }

  public void setOptions(CompilerOptions options) {
    compilationLevel.setOptionsForCompilationLevel(options);

    if (debug) {
      compilationLevel.setDebugOptionsForCompilationLevel(options);
    }

    if (useTypesForOptimization) {
      compilationLevel.setTypeBasedOptimizationOptions(options);
    }

    if (assumeFunctionWrapper) {
      compilationLevel.setWrappedOutputOptimizations(options);
    }
  }
}
