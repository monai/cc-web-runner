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
import org.pmw.tinylog.Logger;

import javax.ws.rs.ApplicationPath;
import java.io.IOException;

@ApplicationPath("/")
public class Application extends ResourceConfig {
  public final static DefaultExterns defaultExterns;

  static {
    DefaultExterns externs = null;
    try {
      externs = new DefaultExterns();
    } catch (IOException e) {
      e.printStackTrace();
    }
    defaultExterns = externs;
  }

  public Application() {
    Genson genson = new GensonBuilder()
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
    int port = Integer.parseInt(System.getProperty("port", "8080"));

    Server server = new Server(port);
    Application app = new Application();
    AccessLog accessLog = new AccessLog();

    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    server.setHandler(context);
    server.setRequestLog(accessLog);

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
      try {
        server.destroy();
      } catch (Throwable error) {
        Logger.error(error);
        System.exit(1);
      }
    }
  }
}
