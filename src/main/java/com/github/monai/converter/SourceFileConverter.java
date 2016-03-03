package com.github.monai.converter;

import com.google.javascript.jscomp.SourceFile;
import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;

public class SourceFileConverter implements Converter<SourceFile> {
  public void serialize(SourceFile object, ObjectWriter writer, Context ctx) throws Exception {
    throw new UnsupportedOperationException();
  }

  public SourceFile deserialize(ObjectReader reader, Context ctx) throws Exception {
    String fileName = "";
    String code = "";

    reader.beginObject();

    while (reader.hasNext()) {
      reader.next();
      if ("fileName".equals(reader.name())) {
        fileName = reader.valueAsString();
      } else if ("code".equals(reader.name())) {
        code = reader.valueAsString();
      } else {
        reader.skipValue();
      }
    }

    reader.endObject();
    return SourceFile.fromCode(fileName, code);
  }
}
