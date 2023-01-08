package com.ukj.exam.board;

import java.util.*;

public class Main {
  public static void main(String[] args) {
    new App().run();
  }
  // main으로 모든 것을 만들어서 static을 써야 했지만, App에다 만들어놓은 로직을 다 옮겨,
  // App 객체로 만들면 static을 쓸 필요가 없다.
}