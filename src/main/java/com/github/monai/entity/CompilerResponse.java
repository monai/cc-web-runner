package com.github.monai.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.javascript.jscomp.Result;

public class CompilerResponse extends AbstractResponse {
  private final Result result;
  private final String source;

  public CompilerResponse(@JsonProperty("results") Result result,
                          @JsonProperty("source") String source) {
    super(StatusCode.SUCCESS);
    this.result = result;
    this.source = source;
  }
}
