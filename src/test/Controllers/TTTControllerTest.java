package test.Controllers;

import org.junit.Test;

import dasBoot.Responses.iResponse;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import mocks._Request;
import Aufbrezeln.Controllers.TTTController;
import Aufbrezeln.Responses.*;


public class TTTControllerTest {
  @Test public void returnsIndexWhenNoHomeRouteCalled() throws Exception {
    _Request request = new _Request();
    TTTController controller = new TTTController();
    request.stubRequest("GET", "/");
    String code = controller.handle(request).getResponseLine().get("code");
    assertEquals("200", code);
  }

  @Test public void returnsClojureJSONNewGameResponseWhenRequested() throws Exception {
    _Request request = new _Request();
    TTTController controller = new TTTController();
    request.stubRequest("POST", "/game/new-game");
    iResponse response = controller.handle(request);
    assertThat(response, instanceOf(ClojureJSONNewGame.class));
  }

  @Test public void returnsClojureJSONMakeMoveResponseWhenRequested() throws Exception {
    _Request request = new _Request();
    TTTController controller = new TTTController();
    request.stubRequest("POST", "/game/make-move");
    iResponse response = controller.handle(request);
    assertThat(response, instanceOf(ClojureJSONMakeMove.class));
  }
}
