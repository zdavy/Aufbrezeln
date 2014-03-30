package Aufbrezeln.Controllers;

import java.io.File;

import dasBoot.Requests.iRequest;
import dasBoot.Responses.iResponse;

import umwelt.Controllers.UmweltController;

public class AssetController extends UmweltController {
  public AssetController(String dir) {
    DIR = dir + "/public/";
  }

  @Override
  public boolean valid(iRequest request) {
    System.out.println(new File(DIR + request.uri()).exists() && !request.uri().equals("/"));
    return new File(DIR + request.uri()).exists() && !request.uri().equals("/");
  }

  @Override
  public iResponse handle(iRequest request) throws Exception {
    newFactory();
    if (!request.method().equals("GET")) {
      return factory.MethodNotAllowed();
    } else {
      return factory.get(request);
    }
  }
}
