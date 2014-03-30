package Aufbrezeln.Controllers;

import Aufbrezeln.Responses.HomepageResponse;
import Aufbrezeln.Responses.TTTFactory;

import umwelt.Controllers.UmweltController;

public class TTTController extends UmweltController {
  public TTTController() {
    DIR += "/public";
    get("/", new HomepageResponse(DIR, "index"));
  }

  @Override
  public void newFactory() {
    factory = new TTTFactory(DIR);
  }
}
