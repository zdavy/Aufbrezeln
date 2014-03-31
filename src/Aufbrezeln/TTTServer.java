package Aufbrezeln;

import Aufbrezeln.Controllers.AssetController;
import Aufbrezeln.Controllers.TTTController;

import umwelt.UmweltServer;
import umwelt.Responses.UmweltFactory;

public class TTTServer extends UmweltServer {
  public TTTServer() throws Exception {
    super(3000);
    addController(new TTTController(), new AssetController(DIR));
    addResponseFactory(new UmweltFactory(DIR));
  }
}
