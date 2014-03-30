package Aufbrezeln.Responses;

import dasBoot.Requests.iRequest;
import dasBoot.Responses.iResponse;

import umwelt.Responses.UmweltResponse;

public class HomepageResponse extends UmweltResponse {
  String DIR;

  public HomepageResponse(String dir) {
    DIR = dir;
  }

  @Override
  public iResponse proccess(iRequest request) {
    try{
      return new TTTFactory(DIR).get(request);
    } catch (Exception e) {
      setStatus("500", "Internal Server Error");
      setVersion("HTML/1.1");
      setContent("Das Boot is kaput");
      return this;
    }
  }
}
