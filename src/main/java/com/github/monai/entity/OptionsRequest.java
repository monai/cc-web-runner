package com.github.monai.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.javascript.jscomp.CompilerOptions;

public class OptionsRequest {
  public final Optimizations optimizations;
  public final CompilerOptions options;

  public OptionsRequest(@JsonProperty("optimizations") Optimizations optimizations,
                        @JsonProperty("options") CompilerOptions options) {
    this.optimizations = optimizations;
    this.options = null != options ? options : new CompilerOptions();
  }
}
