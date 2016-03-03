package com.github.monai;

import com.google.javascript.jscomp.BasicErrorManager;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.JSError;

public class VoidErrorManager extends BasicErrorManager {
  @Override
  public void println(CheckLevel level, JSError error) {
    // void
  }

  @Override
  protected void printSummary() {
    // void
  }
}
