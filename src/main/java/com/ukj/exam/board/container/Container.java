package com.ukj.exam.board.container;

import com.ukj.exam.board.controller.UsrArticleController;
import com.ukj.exam.board.controller.UsrMemberController;
import com.ukj.exam.board.session.Session;
import lombok.Getter;

import java.util.Scanner;

public class Container {
  @Getter
  private static Scanner sc;
  @Getter
  private static Session session;
  @Getter
  private static UsrArticleController usrArticleController;
  @Getter
  private static UsrMemberController usrMemberController;

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
