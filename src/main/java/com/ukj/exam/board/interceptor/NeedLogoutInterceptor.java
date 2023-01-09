package com.ukj.exam.board.interceptor;

import com.ukj.exam.board.vo.Rq;

public class NeedLogoutInterceptor implements Interceptor {
  @Override
  public boolean run(Rq rq) {
    if (!rq.isLogged()) { //로그아웃인 상태
      return true;
    }

    // 로그인인 상태
    switch (rq.getUrlPath()) { // url에 따라 로그아웃이 필요한 서비스를 허용함
      case "/usr/member/login":
      case "/usr/member/join":
      case "/usr/member/findLoginId":
      case "/usr/member/findLoginPw":
        System.out.println("이미 로그인 되었습니다.");
        return false;
    }

    return true;
  }
}
