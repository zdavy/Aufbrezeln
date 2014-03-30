package Aufbrezeln;

import Aufbrezeln.Controllers.AssetController;
import Aufbrezeln.Controllers.TTTController;
import Aufbrezeln.Responses.TTTFactory;

import umwelt.UmweltServer;

public class TTTServer extends UmweltServer {
  public TTTServer() throws Exception {
    super(3000);
    addController(new TTTController(), new AssetController(DIR));
    addResponseFactory(new TTTFactory(DIR));
  }
}
