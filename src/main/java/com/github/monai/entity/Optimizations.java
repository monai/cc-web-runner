package com.github.monai.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.javascript.jscomp.CompilationLevel;

public class Optimizations {
  public final CompilationLevel level;
  public final Boolean debug;
  public final Boolean typeBased;
  public final Boolean wrappedOutput;

  public Optimizations(@JsonProperty("level") CompilationLevel level,
                       @JsonProperty("debug") Boolean debug,
                       @JsonProperty("typeBased") Boolean typeBased,
                       @JsonProperty("wrappedOutput") Boolean wrappedOutput) {
    this.level = level;
    this.debug = null != debug ? debug : false;
    this.typeBased = null != typeBased ? typeBased : false;
    this.wrappedOutput = null != wrappedOutput ? wrappedOutput : false;
  }
}
