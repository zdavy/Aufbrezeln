package Aufbrezeln.Controllers;

import Aufbrezeln.Responses.HomepageResponse;

import umwelt.Controllers.UmweltController;

public class TTTController extends UmweltController {
  public TTTController() {
    DIR += "/public";
    get("/", new HomepageResponse(DIR));
  }
}
