package com.ukj.exam.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {

  void run() {
    Scanner sc = Container.sc;
    Session session = Container.getSession();

    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");

    while (true) {
      Member loggedMember = (Member) session.getAttribute("loggedMember");

      String promptName = "명령어";

      if (loggedMember != null) {
        promptName = loggedMember.loginId;
      }

      System.out.printf("%s > ", promptName);
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);
      Map<String, String> params = rq.getQueryParams();

      if (rq.getUrlPath().equals("exit")) {
        break;

      } else if (rq.getUrlPath().equals("/usr/article/list")) { // 리스트 출력
        Container.usrArticleController.showList(rq);

      } else if (rq.getUrlPath().equals("/usr/article/detail")) { // 상세정보 출력
        Container.usrArticleController.actionDetail(rq);

      } else if (rq.getUrlPath().equals("/usr/article/write")) { // 게시물 입력
        Container.usrArticleController.actionWrite();

      } else if (rq.getUrlPath().equals("/usr/article/modify")) { // 게시물 수정
        Container.usrArticleController.actionModify(rq);

      } else if (rq.getUrlPath().equals("/usr/article/delete")) { // 게시물 삭제
        Container.usrArticleController.actionDelete(rq);

      } else if (rq.getUrlPath().equals("/usr/member/join")) {
        Container.usrMemberController.actionJoin();

      } else if (rq.getUrlPath().equals("/usr/member/login")) {
        Container.usrMemberController.actionLogin(rq);
      } else {
        System.out.printf("입력 된 명령어: %s\n", cmd);
      }
    }

    System.out.println("== 프로그램 종료 ==");
    sc.close();
  }
}