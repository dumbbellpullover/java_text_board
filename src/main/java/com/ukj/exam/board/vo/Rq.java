package com.ukj.exam.board.vo;

import com.ukj.exam.board.container.Container;
import com.ukj.exam.board.session.Session;
import com.ukj.exam.board.util.Util;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class Rq {
  private String url;
  private Map<String, String> queryParams;
  private String urlPath;

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

  public Object getSessionAttr(String key) {
    Session session = Container.getSession();
    return session.getAttribute(key);
  }

  public void setSessionAttr(String key, Object value) {
    Session session = Container.getSession();
    session.setAttribute(key, value);
  }

  public void removeSessionAttr(String key) {
    Session session = Container.getSession();
    session.removeAttribute(key);
  }

  public boolean hasSessionAttr(String key) {
    Session session = Container.getSession();
    return session.hasAttribute(key);
  }

  public Member getLoggedMember() {
    return (Member) getSessionAttr("loggedMember");
  }

  public boolean isLogged() {
    return hasSessionAttr("loggedMember");
  }

  public void setCommand(String url) {
    urlPath = Util.getUrlPathFromUrl(url);
    queryParams = Util.getQueryParamsFromUrl(url);
  }

  public void login(Member member) {
    setSessionAttr("loggedMember", member);
  }

  public void logout() {
    removeSessionAttr("loggedMember");
  }

  public int getLoggedMemberID() {
    return getLoggedMember().getId();
  }
}

