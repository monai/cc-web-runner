package com.github.monai;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.RequestLog;
import org.eclipse.jetty.server.Response;
import org.pmw.tinylog.Logger;

public class AccessLog implements RequestLog {
  @Override
  public void log(Request request, Response response) {
    Logger.info(request);
  }
}
