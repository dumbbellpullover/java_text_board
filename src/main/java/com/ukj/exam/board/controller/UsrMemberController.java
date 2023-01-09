package com.ukj.exam.board.controller;

import com.ukj.exam.board.container.Container;
import com.ukj.exam.board.service.MemberService;
import com.ukj.exam.board.vo.Rq;
import com.ukj.exam.board.vo.Member;

import java.util.ArrayList;
import java.util.List;

public class UsrMemberController {

  private MemberService memberService;

  public UsrMemberController() {
    memberService = Container.getMemberService();

    memberService.makeTestData();

  }

  public void actionJoin() {
    System.out.println("== 회원 가입 ==");
    System.out.print("로그인 아이디: ");
    String loginId = Container.getSc().nextLine();
    System.out.print("로그인 패스워드: ");
    String loginPw = Container.getSc().nextLine();
    System.out.print("로그인 PW 확인: ");
    String loginPwConfirm = Container.getSc().nextLine();

    if (!loginPw.equals(loginPwConfirm)) {
      System.out.println("비밀번호가 일치하지 않습니다.");
      return;
    }

    int id = memberService.join(loginId, loginPw);

    System.out.printf("&s님 가입을 환영합니다.\n", loginId);
    System.out.printf("%d번째 회원이 생성되었습니다.\n", id);
  }

  public void actionLogin(Rq rq) {
    System.out.printf("로그인 ID: ");
    String loginId = Container.getSc().nextLine().trim();

    if (loginId.length() == 0) {
      System.out.println("로그인 ID를 입력해주세요.");
      return;
    }

    Member member = memberService.getMemberByLoginId(loginId);

    if (member == null) {
      System.out.println("해당 회원은 존재하지 않습니다.");
      return;
    }

    System.out.printf("로그인 PW: ");
    String loginPw = Container.getSc().nextLine().trim();

    if (loginPw.length() == 0) {
      System.out.println("로그인 PW를 입력해주세요.");
      return;
    }

    if (!member.getLoginPw().equals(loginPw)) {
      System.out.println("PW가 일치하지 않습니다.");
      return;
    }

    rq.login(member);

    System.out.printf("%s님, 환영합니다.\n", member.getLoginId());
  }

  public void actionLogout(Rq rq) {
    if (!rq.isLogged()) {
      System.out.println("로그인 후 이용해주세요.");
    }

    rq.logout();
    System.out.println("로그아웃 되었습니다.");
  }
}
