package Aufbrezeln.Responses.JSON;

import java.util.Hashtable;

import Aufbrezeln.Responses.JSONResponse;
import WebInterface.TTTService;

public class MakeMove extends JSONResponse {

  @Override
  public String evaluate(Hashtable<String, String> data) {
    return TTTService.play(data);
  }
}
