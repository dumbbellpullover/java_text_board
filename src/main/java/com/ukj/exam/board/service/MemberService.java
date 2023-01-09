package com.ukj.exam.board.service;

import com.ukj.exam.board.repository.MemberRepository;
import com.ukj.exam.board.vo.Member;

public class MemberService {

  private MemberRepository memberRepository;

  public MemberService() {
    memberRepository = new MemberRepository(); // new 로 꼭 만들어야
  }

  public void makeTestData() {
    for (int i = 1; i <= 3; i++) {
      String loginId = "user" + i;
      String loginPw = "user" + i;

      join(loginId, loginPw);
    }
  }

  public int join(String loginId, String loginPw) {
    return memberRepository.join(loginId, loginPw);
  }

  public Member getMemberByLoginId(String loginId) {
    return memberRepository.getMemberByLoginId(loginId);
  }

  public Member getMemberById(int id) {
    return memberRepository.getMemberById(id);
  }
}

