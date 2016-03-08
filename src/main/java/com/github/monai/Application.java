package com.github.monai;

import com.github.monai.converter.CharsetConverter;
import com.github.monai.converter.JSErrorConverter;
import com.github.monai.converter.SourceFileConverter;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import com.owlike.genson.ext.jaxrs.GensonJaxRSFeature;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

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

  public static void main(String[] args) throws Exception {
    Server server = new Server(8080);
    Application app = new Application();

    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    server.setHandler(context);

    ServletContainer servletContainer = new ServletContainer(app);
    ServletHolder servlet = new ServletHolder(servletContainer);
    context.addServlet(servlet, "/*");

    servlet.setInitParameter(
            "jersey.config.server.provider.classnames",
            Application.class.getCanonicalName());

    try {
      server.start();
      server.join();
    } finally {
      server.destroy();
    }
  }
}
