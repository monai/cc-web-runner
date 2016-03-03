package com.github.monai.converter;

import com.google.javascript.jscomp.JSError;
import com.owlike.genson.Context;
import com.owlike.genson.Converter;
import com.owlike.genson.stream.ObjectReader;
import com.owlike.genson.stream.ObjectWriter;

public class JSErrorConverter implements Converter<JSError> {
  public void serialize(JSError object, ObjectWriter writer, Context ctx) throws Exception {
    writer.beginObject();

    writer.writeName("type");
    ctx.genson.serialize(object.getType(), writer, ctx);

    writer.writeName("description");
    ctx.genson.serialize(object.description, writer, ctx);

    writer.writeString("sourceName", object.sourceName);

//    writer.writeName("node");
//    ctx.genson.serialize(object.node, writer, ctx);

    writer.writeNumber("lineNumber", object.lineNumber);

    writer.writeName("defaultLevel");
    ctx.genson.serialize(object.getDefaultLevel(), writer, ctx);

    writer.writeNumber("charno", object.getCharno());

    writer.endObject();
  }

  public JSError deserialize(ObjectReader reader, Context ctx) throws Exception {
    throw new UnsupportedOperationException();
  }
}
