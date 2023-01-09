package com.ukj.exam.board;

import java.util.Map;

class Rq {
  String url;
  Map<String, String> queryParams;
  String urlPath;

  Rq(String url) {
    this.url = url;
    this.queryParams = com.ukj.exam.board.Util.getQueryParamsFromUrl(this.url);
    this.urlPath = com.ukj.exam.board.Util.getUrlPathFromUrl(this.url);
  }

  public int getIntParam(String paramName, int defaultValue) {

    if(!queryParams.containsKey(paramName)) {
      return defaultValue;
    }

    try {
      return Integer.parseInt(queryParams.get(paramName));
    }
    catch( NumberFormatException e) {
      return defaultValue;
    }
  }

  public String getParam(String paramName, String defaultValue) {

    if(!queryParams.containsKey(paramName)) {
      return defaultValue;
    }

    return paramName;
  }

  public Map<String, String> getQueryParams() {
    return this.queryParams;
  }

  public String getUrlPath() {
    return this.urlPath;
  }

  public void setSessionAttr(String key, Object value) {
    com.ukj.exam.board.Session session = com.ukj.exam.board.Container.getSession();

    session.setAttribute(key, value);
  }

  public void removeSessionAttr(String key) {
    com.ukj.exam.board.Session session = com.ukj.exam.board.Container.getSession();

    session.removeAttribute(key);
  }
}
