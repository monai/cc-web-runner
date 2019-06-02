package com.github.monai.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.google.javascript.jscomp.ConformanceConfig;
import com.google.protobuf.TextFormat;

import java.io.IOException;

public class ConformanceConfigDeserializer extends StdDeserializer<ConformanceConfig> {
    ConformanceConfigDeserializer() {
        this(null);
    }

    private ConformanceConfigDeserializer(Class<ConformanceConfig> t) {
        super(t);
    }

    @Override
    public ConformanceConfig deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {

        ConformanceConfig.Builder builder = ConformanceConfig.newBuilder();
        TextFormat.getParser().merge(jp.getValueAsString(), builder);

        return builder.build();
    }
}

