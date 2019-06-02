package com.github.monai.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.javascript.jscomp.CompilerOptions;

public class OptionsRequest {
  public final CompilationLevelOptions compilationLevelOptions;
  public final CompilerOptions options;

  public OptionsRequest(@JsonProperty("compilationLevelOptions") CompilationLevelOptions compilationLevelOptions,
                        @JsonProperty("options") CompilerOptions options) {
    this.compilationLevelOptions = compilationLevelOptions;
    this.options = null != options ? options : new CompilerOptions();
  }
}
