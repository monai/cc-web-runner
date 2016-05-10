package com.github.monai;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.RequestLog;
import org.eclipse.jetty.server.Response;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

public class AccessLog implements RequestLog {
  @Override
  public void log(Request request, Response response) {
    Logger.info(request);

    if (Logger.getLevel().compareTo(Level.DEBUG) == 1) {
      Logger.info(formatResponse(response));
    } else {
      Logger.debug(response.toString());
    }
  }

  private String formatResponse(Response response) {
    return String.format("%s[%s %d %s]@%x",
            response.getClass().getSimpleName(),
            response.getHttpChannel().getRequest().getHttpVersion(),
            response.getStatus(),
            response.getReason(),
            response.hashCode());
  }
}
