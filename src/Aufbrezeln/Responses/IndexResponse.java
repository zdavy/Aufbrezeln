package Aufbrezeln.Responses;

import dasBoot.Requests.iRequest;
import dasBoot.Responses.iResponse;

import umwelt.Responses.UmweltFactory;
import umwelt.Responses.UmweltResponse;

public class IndexResponse extends UmweltResponse {
  String DIR;
  String HOMEPAGE = "/index";

  public IndexResponse(String dir) {
    DIR = dir;
  }

  @Override
  public iResponse proccess(iRequest request) {
    try{
      return new UmweltFactory(DIR).get(HOMEPAGE);
    } catch (Exception e) { return this; }
  }
}
