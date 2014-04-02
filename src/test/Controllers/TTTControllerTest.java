package test.Controllers;

import org.junit.Test;

import dasBoot.Responses.iResponse;

import mocks._Request;

import static org.junit.Assert.*;

import Aufbrezeln.Controllers.TTTController;


public class TTTControllerTest {
  @Test public void returnsIndexWhenNoRouteCalled() throws Exception {
    _Request request = new _Request();
    TTTController controller = new TTTController();
    request.stubRequest("GET", "/");
    iResponse response = controller.handle(request);

    String code = response.getResponseLine().get("code");
    assertEquals("200", code);

    String contentType = response.getHeader().get("Content-Type");
    assertTrue(contentType.equals("text/html"));
  }

  @Test public void JSONNewGameResponseWhenRequested() throws Exception {
    _Request request = new _Request();
    TTTController controller = new TTTController();
    request.stubRequest("POST", "/game/new-game");
    iResponse response = controller.handle(request);

    String contentType = response.getHeader().get("Content-Type");
    assertTrue(contentType.equals("application/json"));
  }

  @Test public void JSONMakeMoveResponseWhenRequested() throws Exception {
    _Request request = new _Request();
    TTTController controller = new TTTController();
    request.stubRequest("POST", "/game/make-move");
    iResponse response = controller.handle(request);

    String contentType = response.getHeader().get("Content-Type");
    assertTrue(contentType.equals("application/json"));
  }
}
