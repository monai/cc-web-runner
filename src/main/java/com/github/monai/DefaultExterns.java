package com.github.monai;

import com.google.javascript.jscomp.CommandLineRunner;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.SourceFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public final class DefaultExterns {
  public final HashMap<CompilerOptions.Environment, List<SourceFile>> externs;


  public DefaultExterns() throws IOException {
    this.externs = new HashMap<>();

    for (CompilerOptions.Environment env : CompilerOptions.Environment.values()) {
      CompilerOptions options = new CompilerOptions();
      options.setEnvironment(env);

      List<SourceFile> externs = CommandLineRunner.getBuiltinExterns(options);
      this.externs.put(env, externs);
    }
  }
}
