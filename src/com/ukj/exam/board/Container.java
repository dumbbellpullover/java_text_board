package com.ukj.exam.board;

import java.util.Scanner;

public class Container {
  static Scanner sc;
  static Session session;
  static UsrArticleController usrArticleController;
  static UsrMemberController usrMemberController;

  static {
    sc = new Scanner(System.in);
    session = new Session();
    usrArticleController = new UsrArticleController();
    usrMemberController = new UsrMemberController();
  }

  public static Session getSession() {
    return session;
  }
}
