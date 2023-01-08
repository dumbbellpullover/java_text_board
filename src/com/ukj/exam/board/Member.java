package com.ukj.exam.board;

public class Member {
  int id;
  String loginId;
  String loginPw;

  Member(int id, String loginId, String loginPw) {
    this.id = id;
    this.loginId = loginId;
    this.loginPw = loginPw;
  }

  @Override
  public String toString() {
    String loginId = this.loginId != null ? "\"" + this.loginId + "\"" : null;
    String loginPw = this.loginPw != null ? "\"" + this.loginId + "\"" : null;

    return String.format("{id: %d, title: %s, body: %s}", this.id, this.loginId, this.loginPw);
  }
}
