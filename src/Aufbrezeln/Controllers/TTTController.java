package Aufbrezeln.Controllers;

import Aufbrezeln.Responses.*;

import umwelt.Controllers.UmweltController;

public class TTTController extends UmweltController {
  public TTTController() {
    DIR += "/public";
    get("/", new IndexResponse(DIR));
    post("/game/new-game", new ClojureJSONNewGame());
    post("/game/make-move", new ClojureJSONMakeMove());
  }
}
