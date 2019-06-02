package com.github.monai.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.javascript.jscomp.ComposeWarningsGuard;
import com.google.javascript.jscomp.RenamingMap;

import java.util.Map;
import java.util.Set;

abstract class CompilerOptionsMixIn {
  @JsonIgnore
  public abstract void setIdGenerators(Map<String, RenamingMap> idGenerators);

  @JsonProperty("idGenerators")
  public abstract void setIdGenerators(Set<String> idGenerators);

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private ComposeWarningsGuard warningsGuard;
}
