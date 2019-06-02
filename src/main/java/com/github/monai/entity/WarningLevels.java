package com.github.monai.entity;

import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.DiagnosticGroup;
import com.google.javascript.jscomp.DiagnosticGroups;

import java.util.HashMap;
import java.util.Map;

public class WarningLevels extends HashMap<String, String> {
  public void setOptions(CompilerOptions options) {
    CheckLevel value;

    for (Map.Entry<String, String> entry : entrySet()) {
      try {
        value = CheckLevel.valueOf(entry.getValue());
      } catch (IllegalArgumentException ex) {
        continue;
      }
      DiagnosticGroup type = DiagnosticGroups.forName(entry.getKey());
      if (null != type) {
        options.setWarningLevel(type, value);
      }
    }
  }
}
