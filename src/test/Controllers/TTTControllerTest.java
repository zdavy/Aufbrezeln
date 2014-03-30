package test.Controllers;

import org.junit.Test;
import static org.junit.Assert.*;

import mocks._Request;
import Aufbrezeln.Controllers.TTTController;


public class TTTControllerTest {
  @Test public void returnsIndexWhenNoHomeRouteCalled() throws Exception {
    _Request request = new _Request();
    TTTController controller = new TTTController();
    request.stubRequest("GET", "/");
    String code = controller.handle(request).getResponseLine().get("code");
    assertEquals("200", code);
  }
}
