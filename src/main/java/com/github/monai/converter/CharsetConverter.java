package com.github.monai.converter;

import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;

import java.nio.charset.Charset;

public class CharsetConverter implements Converter<Charset> {
  public void serialize(Charset charset, ObjectWriter objectWriter, Context context) throws Exception {
    objectWriter.writeString(charset.toString());
  }

  public Charset deserialize(ObjectReader objectReader, Context context) throws Exception {
    return Charset.forName(objectReader.valueAsString());
  }
}
