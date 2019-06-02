package com.github.monai.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.javascript.jscomp.CompilerOptions;

public class OptionsRequest {
  public final CompilationLevelOptions compilationLevelOptions;
  public final WarningLevels warningLevels;
  public final CompilerOptions options;

  public OptionsRequest(@JsonProperty("compilationLevelOptions") CompilationLevelOptions compilationLevelOptions,
                        @JsonProperty("warningLevels") WarningLevels warningLevels,
                        @JsonProperty("options") CompilerOptions options) {
    this.compilationLevelOptions = null != compilationLevelOptions ? compilationLevelOptions : new CompilationLevelOptions();
    this.options = null != options ? options : new CompilerOptions();
    this.warningLevels = null != warningLevels ? warningLevels : new WarningLevels();
  }
}
