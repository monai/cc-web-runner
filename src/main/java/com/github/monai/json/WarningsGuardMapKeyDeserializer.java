package com.github.monai.json;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.google.javascript.jscomp.WarningsGuard;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class WarningsGuardMapKeyDeserializer extends KeyDeserializer {
  @Override
  public WarningsGuard deserializeKey(String key, DeserializationContext ctx) throws IOException {

    InputStream is = new ByteArrayInputStream(key.getBytes(StandardCharsets.UTF_8));
    ObjectInputStream ois = new ObjectInputStream(is);

    try {
      WarningsGuard wg = (WarningsGuard) ois.readObject();
      return wg;
    } catch (ClassNotFoundException ex) {
      return null;
    } finally {
      ois.close();
    }
  }
}

