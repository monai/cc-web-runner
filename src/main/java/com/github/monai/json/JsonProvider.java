package com.github.monai.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.ConformanceConfig;
import com.google.javascript.jscomp.SourceFile;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JsonProvider extends JacksonJaxbJsonProvider {

  private static ObjectMapper mapper = new ObjectMapper();

  static {
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(DeserializationFeature.EAGER_DESERIALIZER_FETCH, true);
    mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
    mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    mapper.enable(SerializationFeature.INDENT_OUTPUT);

    mapper.addMixIn(CompilerOptions.AliasTransformationHandler.class, AliasTransformationHandlerMixIn.class);
    mapper.addMixIn(CompilerOptions.class, CompilerOptionsMixIn.class);

    mapper.registerModule(new GuavaModule());

    SimpleModule module = new SimpleModule();
    module.addSerializer(ConformanceConfig.class, new ConformanceConfigSerializer());
    module.addDeserializer(ConformanceConfig.class, new ConformanceConfigDeserializer());
    module.addDeserializer(SourceFile.class, new SourceFileDeserializer());
    mapper.registerModule(module);
  }

  public JsonProvider() {
    super();
    setMapper(mapper);
  }
}