package com.ukj.exam.board;

import java.util.Map;

class Rq {
  String url;
  Map<String, String> queryParams;
  String urlPath;

  Rq(String url) {
    this.url = url;
    this.queryParams = Util.getQueryParamsFromUrl(this.url);
    this.urlPath = Util.getUrlPathFromUrl(this.url);
  }

  public Map<String, String> getQueryParams() {
    return this.queryParams;
  }

  public String getUrlPath() {
    return this.urlPath;
  }
}
