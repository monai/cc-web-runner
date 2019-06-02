package com.github.monai.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.SourceFile;

import java.util.ArrayList;
import java.util.List;

public class CompilerRequest {
  public final CompilationLevelOptions compilationLevelOptions;
  public final CompilerOptions options;
  public final List<SourceFile> externs;
  public final List<SourceFile> sources;

  public CompilerRequest(@JsonProperty("compilationLevel") CompilationLevelOptions compilationLevelOptions,
                         @JsonProperty("options") CompilerOptions options,
                         @JsonProperty("externs") List<SourceFile> externs,
                         @JsonProperty("sources") List<SourceFile> sources) {
    this.compilationLevelOptions = compilationLevelOptions;
    this.options = null != options ? options : new CompilerOptions();
    this.externs = null != externs ? externs : new ArrayList<>();
    this.sources = null != sources ? sources : new ArrayList<>();
  }
}
