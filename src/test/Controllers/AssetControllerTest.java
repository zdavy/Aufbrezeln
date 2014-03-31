package test.Controllers;

import mocks._Request;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import Aufbrezeln.Controllers.AssetController;
import Helpers.FileHelper;

public class AssetControllerTest {
  AssetController controller;
  _Request request;

  @Before public void init() {
    controller = new AssetController(System.getProperty("user.dir"));
    request = new _Request();
  }

  @Test public void DirectoriesAreNotAssets() {
    request.stubRequest("GET", "/");
    assertFalse(controller.valid(request));
  }

  @Test public void FilesInThePublicStaticFolderAreAssets() throws Exception {
    FileHelper.createFile("public/static/test.css");
    request.stubRequest("GET", "/test.css");
    assertTrue(controller.valid(request));
    FileHelper.destroyFile("public/static/test.css");
  }
}
