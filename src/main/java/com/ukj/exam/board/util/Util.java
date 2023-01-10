package com.ukj.exam.board.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {
  public static String getNowDateStr() {
    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    return format1.format(System.currentTimeMillis());
  }

  public static Map<String, String> getQueryParamsFromUrl(String url) {
    Map<String, String> queryParams = new HashMap<>();
    String[] urlBits = url.split("\\?", 2);

    if (urlBits.length == 1) {
      return queryParams;
    }

    for (String queryString : urlBits[1].split("&")) {
      String[] params = queryString.split("=");

      if (params.length == 1) {
        continue;
      }

      queryParams.put(params[0], params[1]);
    }

    return queryParams;
  }

  public static String getUrlPathFromUrl(String url) {
    return url.split("\\?")[0];
  }

  public static <T> List<T> reverseList(List<T> list) {
    List<T> reverse = new ArrayList<>(list.size());

    for (int i = list.size() - 1; i >= 0; i--) {
      reverse.add(list.get(i));
    }
    return reverse;
  }

  public static int getRandomInt(int start, int end) {
    int size = end - start + 1;

    return start + (int) (Math.random() * size);
  }
}
