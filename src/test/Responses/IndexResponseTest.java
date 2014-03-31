package test.Responses;

import mocks._Request;

import org.junit.Test;

import dasBoot.Responses.iResponse;

import Aufbrezeln.Responses.IndexResponse;
import Helpers.FileHelper;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class IndexResponseTest {
IndexResponse response;

  @Test public void getsAssignedHomePage() throws Exception {
    FileHelper.createFile("index.html");
    response = new IndexResponse(System.getProperty("user.dir"));
    iResponse homepage = response.proccess(new _Request());
    String stringContent = new String(homepage.getBody().get("content"), "UTF-8");
    String code = homepage.getResponseLine().get("code");
    assertThat(stringContent, containsString("test"));
    assertTrue(code.equals("200"));
    FileHelper.destroyFile("index.html");
  }

  @Test public void throws400IfNoIndexFound() throws Exception {
    response = new IndexResponse(System.getProperty("user.dir"));
    iResponse homepage = response.proccess(new _Request());
    String code = homepage.getResponseLine().get("code");
    assertTrue(code.equals("404"));
  }
}
