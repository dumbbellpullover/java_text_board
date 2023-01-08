package com.ukj.exam.board;

import java.util.ArrayList;
import java.util.List;

public class UsrMemberController {
  int memberLastId;
  List<Member> members;

  UsrMemberController() {
    memberLastId = 0;
    members = new ArrayList<>();

    makeTestData();

    if (members.size() > 0) {
      memberLastId = members.get(members.size() - 1).id;
    }
  }

  void makeTestData() {
    for (int i = 1; i <= 3; i++) {
      members.add(new Member(i, "user" + i, "pw" + i));
    }
  }

  public void actionJoin() {
    System.out.println("== 회원 가입 ==");
    System.out.print("로그인 아이디: ");
    String loginId = Container.sc.nextLine();
    System.out.print("로그인 패스워드: ");
    String loginPw = Container.sc.nextLine();
    System.out.print("로그인 PW 확인: ");
    String loginPwConfirm = Container.sc.nextLine();

    if (!loginPw.equals(loginPwConfirm)) {
      System.out.println("비밀번호가 일치하지 않습니다.");
      return;
    }

    int id = ++memberLastId;

    Member member = new Member(id, loginId, loginPw);
    members.add(member);

    System.out.printf("&s님 가입을 환영합니다.\n", member.loginId);
    System.out.printf("%d번째 회원이 생성되었습니다.\n", member.id);
  }

  public void actionLogin() {
    System.out.printf("로그인 ID: ");
    String loginId = Container.sc.nextLine().trim();

    if (loginId.length() == 0) {
      System.out.println("로그인 ID를 입력해주세요.");
      return;
    }

    Member member = getMemberLoginId(loginId);

    if (member == null) {
      System.out.println("해당 회원은 존재하지 않습니다.");
      return;
    }

    System.out.printf("로그인 PW: ");
    String loginPw = Container.sc.nextLine().trim();

    if (loginPw.length() == 0) {
      System.out.println("로그인 PW를 입력해주세요.");
      return;
    }

    if (!member.loginPw.equals(loginPw)) {
      System.out.println("PW가 일치하지 않습니다.");
      return;
    }

    System.out.printf("%s님, 환영합니다.\n", member.loginId);
  }

  private Member getMemberLoginId(String loginId) {
    for (Member member : members) {
      if (member.loginId.equals(loginId)) {
        return member;
      }
    }

    return null;
  }
}
