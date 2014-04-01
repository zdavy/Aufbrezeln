package test.Responses;

import mocks._Request;

import org.junit.Test;

import Aufbrezeln.Responses.JSONResponse;

import static org.junit.Assert.*;

public class JSONResponseTest {
  @Test public void PassesJSONContentType() {
    JSONResponse response = new JSONResponse();
    response.proccess(new _Request());
    String contentType = response.getHeader().get("Content-Type");
    assertTrue(contentType.equals("application/json"));
  }
}
