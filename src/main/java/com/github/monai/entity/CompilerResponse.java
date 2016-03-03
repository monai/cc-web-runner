package com.github.monai.entity;

import com.google.javascript.jscomp.Result;

public class CompilerResponse extends AbstractResponse {
  public final Result result;
  public final String source;

  public CompilerResponse(Result result, String source) {
    super(StatusCode.SUCCESS);
    this.result = result;
    this.source = source;
  }
}
