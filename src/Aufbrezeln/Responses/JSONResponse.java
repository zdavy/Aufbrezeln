package Aufbrezeln.Responses;

import java.util.Hashtable;

import dasBoot.Requests.iRequest;
import dasBoot.Responses.iResponse;

import umwelt.Responses.UmweltResponse;

public class JSONResponse extends UmweltResponse {
  @Override
    public iResponse proccess(iRequest request) {
      setStatus("200", "OK");
      setVersion("HTTP/1.1");
      setHeader("Content-Type", "application/json");
      try {
      setContent(evaluate(request.getBody()));
      } catch (Exception e) {  }
      return this;
    }

  public String evaluate(Hashtable<String, String> data) {
    return "";
  }
}
