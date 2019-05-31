package com.github.monai.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.javascript.jscomp.WarningsGuard;

import java.util.HashMap;
import java.util.Map;

abstract public class ComposeWarningsGuardMixIn {
  @JsonDeserialize(keyUsing = WarningsGuardMapKeyDeserializer.class)
  private Map<WarningsGuard, Integer> orderOfAddition = new HashMap<>();
}
