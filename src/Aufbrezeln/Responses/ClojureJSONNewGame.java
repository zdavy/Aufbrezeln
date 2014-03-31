package Aufbrezeln.Responses;

import dasBoot.Requests.iRequest;
import dasBoot.Responses.iResponse;

import umwelt.Responses.UmweltResponse;

public class ClojureJSONNewGame extends UmweltResponse {
  @Override
    public iResponse proccess(iRequest request) {
      return this;
    }
}
