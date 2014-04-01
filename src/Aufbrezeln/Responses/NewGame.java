package Aufbrezeln.Responses;

import java.util.Hashtable;

import WebInterface.interact;

public class NewGame extends JSONResponse {

  @Override
  public String evaluate(Hashtable<String, String> data) {
    return interact.start(data);
  }
}
