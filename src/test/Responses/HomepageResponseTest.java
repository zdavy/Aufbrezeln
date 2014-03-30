package test.Responses;

import mocks._Request;

import org.junit.Test;

import dasBoot.Responses.iResponse;

import Aufbrezeln.Responses.HomepageResponse;
import Helpers.FileHelper;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class HomepageResponseTest {
  @Test public void getsAssignedHomePage() throws Exception {
    FileHelper.createFile("test");

    HomepageResponse response;
    response = new HomepageResponse(System.getProperty("user.dir"), "test");
    iResponse homepage = response.proccess(new _Request());
    String stringContent = new String(homepage.getBody().get("content"), "UTF-8");
    assertThat(stringContent, containsString("test"));

    FileHelper.destroyFile("test");
  }
}
