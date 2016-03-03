package com.github.monai;

import com.github.monai.converter.CharsetConverter;
import com.github.monai.converter.JSErrorConverter;
import com.github.monai.converter.SourceFileConverter;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import com.owlike.genson.ext.jaxrs.GensonJaxRSFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class Application extends ResourceConfig {
  public Application() {
    Genson genson = new GensonBuilder()
            .setSkipNull(true)
            .useIndentation(true)
            .useConstructorWithArguments(true)
            .withConverters(new CharsetConverter())
            .withConverters(new SourceFileConverter())
            .withConverters(new JSErrorConverter())
            .create();

    packages("com.github.monai.resource");
    register(new GensonJaxRSFeature().use(genson));
    register(GenericExceptionMapper.class);
  }
}
