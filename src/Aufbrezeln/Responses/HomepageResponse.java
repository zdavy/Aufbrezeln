package Aufbrezeln.Responses;

import dasBoot.Requests.iRequest;
import dasBoot.Responses.iResponse;

import umwelt.Responses.UmweltResponse;

public class HomepageResponse extends UmweltResponse {
  String DIR;
  String HOMEPAGE;

  public HomepageResponse(String dir, String homepage) {
    DIR = dir;
    HOMEPAGE = "/" + homepage;
  }

  @Override
  public iResponse proccess(iRequest request) {
    try{
      return new TTTFactory(DIR).get(HOMEPAGE);
    } catch (Exception e) {
      setStatus("500", "Internal Server Error");
      setVersion("HTML/1.1");
      setContent("Das Boot is kaput");
      return this;
    }
  }
}
