package Aufbrezeln.Responses;

import java.io.IOException;

import dasBoot.Responses.iResponse;

import umwelt.Responses.UmweltFactory;
import umwelt.Responses.UmweltResponse;

public class TTTFactory extends UmweltFactory {
  public TTTFactory(String dir) {
    super(dir);
  }

  public iResponse get(String request) throws IOException {
    response = new UmweltResponse();
    set200();
    response.setHeader("Content-Type", getMIMEType(extend(request)));
    System.out.println(extend(request));
    getFile(extend(request));
    return response;
  }
}
