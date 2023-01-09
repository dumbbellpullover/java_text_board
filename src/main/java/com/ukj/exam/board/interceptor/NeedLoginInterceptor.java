package com.ukj.exam.board.interceptor;

import com.ukj.exam.board.vo.Rq;

public class NeedLoginInterceptor implements Interceptor {
  @Override
  public boolean run(Rq rq) {
    if (rq.isLogged()) { // 로그인인 상태
      return true;
    }

    // 로그아웃인 상태
    switch (rq.getUrlPath()) { // url에 따라 로그인이 필요한 서비스를 허용함
      case "/usr/article/write":
      case "/usr/article/modify":
      case "/usr/article/delete":
      case "/usr/member/logout":
        System.out.println("로그인 후 이용해주세요.");
        return false;
    }

    return true;
  }
}
