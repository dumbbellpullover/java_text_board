package com.ukj.exam.board;

import com.ukj.exam.board.container.Container;
import com.ukj.exam.board.interceptor.Interceptor;
import com.ukj.exam.board.vo.Member;
import com.ukj.exam.board.vo.Rq;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

  void run() {
    Scanner sc = Container.getSc();

    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");

    while (true) {
      Rq rq = new Rq();

      String promptName = "명령어";

      if (rq.isLogged()) {
        Member loggedMember = rq.getLoggedMember();
        promptName = loggedMember.getLoginId();
      }

      System.out.printf("%s > ", promptName);
      String cmd = sc.nextLine();

      rq.setCommand(cmd);

      if (runInterceptors(rq) == false) {
        continue; // while문 맨 처음으로
      }

      if (rq.getUrlPath().equals("exit")) {
        break;

      } else if (rq.getUrlPath().equals("/usr/article/list")) { // 리스트 출력
        Container.getUsrArticleController().showList(rq);

      } else if (rq.getUrlPath().equals("/usr/article/detail")) { // 상세정보 출력
        Container.getUsrArticleController().actionDetail(rq);

      } else if (rq.getUrlPath().equals("/usr/article/write")) { // 게시물 입력
        Container.getUsrArticleController().actionWrite();

      } else if (rq.getUrlPath().equals("/usr/article/modify")) { // 게시물 수정
        Container.getUsrArticleController().actionModify(rq);

      } else if (rq.getUrlPath().equals("/usr/article/delete")) { // 게시물 삭제
        Container.getUsrArticleController().actionDelete(rq);

      } else if (rq.getUrlPath().equals("/usr/member/join")) {
        Container.getUsrMemberController().actionJoin();

      } else if (rq.getUrlPath().equals("/usr/member/login")) {
        Container.getUsrMemberController().actionLogin(rq);

      } else if (rq.getUrlPath().equals("/usr/member/logout")) {
        Container.getUsrMemberController().actionLogout(rq);

      } else {
        System.out.printf("입력 된 명령어: %s\n", cmd);
      }
    }

    System.out.println("== 프로그램 종료 ==");
    sc.close();
  }

  private boolean runInterceptors(Rq rq) {
    List<Interceptor> interceptors = new ArrayList<>();

    interceptors.add(Container.getNeedLoginInterceptor());
    interceptors.add(Container.getNeedLogoutInterceptor());

    for (Interceptor interceptor : interceptors) {
      if (!interceptor.run(rq)) {
        return false;
      }
    }
    return true;
  }
}