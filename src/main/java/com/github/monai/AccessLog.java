package com.github.monai;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.RequestLog;
import org.eclipse.jetty.server.Response;
import org.tinylog.Logger;

public class AccessLog implements RequestLog {
  @Override
  public void log(Request request, Response response) {
    Logger.info(request);

    if (Logger.isDebugEnabled()) {
      Logger.debug(response.toString());
    } else {
      Logger.info(formatResponse(response));
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
