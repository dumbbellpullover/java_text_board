package com.ukj.exam.board.vo;

import com.ukj.exam.board.container.Container;
import com.ukj.exam.board.session.Session;
import com.ukj.exam.board.util.Util;

import java.util.Map;

public class Rq {
  private String url;
  private Map<String, String> queryParams;
  private String urlPath;

  public Rq(String url) {
    this.url = url;
    this.queryParams = Util.getQueryParamsFromUrl(this.url);
    this.urlPath = Util.getUrlPathFromUrl(this.url);
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
    Session session = Container.getSession();

    session.setAttribute(key, value);
  }

  public void removeSessionAttr(String key) {
    Session session = Container.getSession();

    session.removeAttribute(key);
  }
}

