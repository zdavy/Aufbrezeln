package test.Controllers;

import org.junit.Test;

import mocks._Request;
import static org.junit.Assert.*;
import Aufbrezeln.Controllers.TTTController;


public class TTTControllerTest {
  @Test public void returnsIndexWhenNoRouteCalled() throws Exception {
    _Request request = new _Request();
    TTTController controller = new TTTController();
    request.stubRequest("GET", "/");
    String code = controller.handle(request).getResponseLine().get("code");
    String contentType = controller.handle(request).getHeader().get("Content-Type");
    assertEquals("200", code);
    assertTrue(contentType.equals("text/html"));
  }

  @Test public void JSONNewGameResponseWhenRequested() throws Exception {
    _Request request = new _Request();
    TTTController controller = new TTTController();
    request.stubRequest("POST", "/game/new-game");
    String contentType = controller.handle(request).getHeader().get("Content-Type");
    assertTrue(contentType.equals("application/json"));
  }

  @Test public void JSONMakeMoveResponseWhenRequested() throws Exception {
    _Request request = new _Request();
    TTTController controller = new TTTController();
    request.stubRequest("POST", "/game/make-move");
    String contentType = controller.handle(request).getHeader().get("Content-Type");
    assertTrue(contentType.equals("application/json"));
  }
}
