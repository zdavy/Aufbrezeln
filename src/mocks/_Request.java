package mocks;

import java.util.Hashtable;

import dasBoot.Requests.iRequest;

public class _Request implements iRequest {
  public String method;
  public String uri;
  public String body;
  public int contentLength;

  public String method() {
    return method;
  }

  public Hashtable<String, String> getBody() {
    return new Hashtable<String, String>();
  }

  public void stubMethod(String stub) {
    method = stub;
  }

  public String uri() {
    return uri;
  }

  public void stubURI(String stub) {
    uri = stub;
  }

  public void stubRequest(String stubMethod, String stubURI) {
    stubMethod(stubMethod);
    stubURI(stubURI);
  }

  public String body(String noop) {
    return body;
  }

  public void stubBody(String stub) {
    body = stub;
  }

  public int contentLength() {
    return contentLength;
  }

  public void stubContentLength(int stub) {
    contentLength = stub;
  }

  public String version() {
    return "HTML/1.1";
  }

  public String header(String noop) {
    return "header";
  }

}
