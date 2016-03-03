package com.github.monai.entity;

import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.SourceFile;

import java.util.List;

public class CompilerRequest {
  public final CompilerOptions options;
  public final List<SourceFile> externs;
  public final List<SourceFile> sources;

  public CompilerRequest(CompilerOptions options,
                         List<SourceFile> externs,
                         List<SourceFile> sources) {
    this.options = options;
    this.externs = externs;
    this.sources = sources;
  }
}
