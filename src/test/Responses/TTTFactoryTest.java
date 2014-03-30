package test.Responses;

import org.junit.Test;

import dasBoot.Responses.iResponse;

import Aufbrezeln.Responses.TTTFactory;
import Helpers.FileHelper;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TTTFactoryTest {
  @Test public void TTTFactoryGetsFileFromUriString() throws Exception {
    FileHelper.createFile("test");

    TTTFactory factory = new TTTFactory(System.getProperty("user.dir"));
    iResponse response = factory.get("/test");
    String stringContent = new String(response.getBody().get("content"), "UTF-8");
    assertThat(stringContent, containsString("test"));

    FileHelper.destroyFile("test");
  }
}
