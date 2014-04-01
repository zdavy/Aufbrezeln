package Aufbrezeln.Responses;

import java.util.Hashtable;

import WebInterface.interact;

public class MakeMove extends JSONResponse {

  @Override
  public String evaluate(Hashtable<String, String> data) {
    return interact.play(data);
  }
}
