package com.github.monai.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.google.javascript.jscomp.ConformanceConfig;
import com.google.protobuf.TextFormat;

import java.io.IOException;

public class ConformanceConfigSerializer extends StdSerializer<ConformanceConfig> {
    ConformanceConfigSerializer() {
        this(null);
    }

    private ConformanceConfigSerializer(Class<ConformanceConfig> t) {
        super(t);
    }

    @Override
    public void serialize(ConformanceConfig value,
                          JsonGenerator jgen,
                          SerializerProvider provider) throws IOException {

        jgen.writeString(TextFormat.printToString(value));
    }
}
