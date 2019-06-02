package com.github.monai.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.google.javascript.jscomp.SourceFile;

import java.io.IOException;

public class SourceFileDeserializer extends StdDeserializer<SourceFile> {
  SourceFileDeserializer() {
    this(null);
  }

  private SourceFileDeserializer(Class<SourceFile> t) {
    super(t);
  }

  @Override
  public SourceFile deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);
    String fileName = node.get("fileName").asText();
    String code = node.get("code").asText();

    return SourceFile.builder().buildFromCode(fileName, code);
  }
}

