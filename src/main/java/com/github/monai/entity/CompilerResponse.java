package com.github.monai.entity;

import com.google.javascript.jscomp.Result;
import com.owlike.genson.annotation.JsonProperty;

public class CompilerResponse extends AbstractResponse {
  public final Result result;
  public final String source;

  public CompilerResponse(@JsonProperty("results") Result result,
                          @JsonProperty("source") String source) {
    super(StatusCode.SUCCESS);
    this.result = result;
    this.source = source;
  }
}
