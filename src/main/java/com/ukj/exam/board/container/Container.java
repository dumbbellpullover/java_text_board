package com.ukj.exam.board.container;

import com.ukj.exam.board.controller.UsrArticleController;
import com.ukj.exam.board.controller.UsrMemberController;
import com.ukj.exam.board.interceptor.NeedLoginInterceptor;
import com.ukj.exam.board.interceptor.NeedLogoutInterceptor;
import com.ukj.exam.board.repository.ArticleRepository;
import com.ukj.exam.board.repository.BoardRepository;
import com.ukj.exam.board.repository.MemberRepository;
import com.ukj.exam.board.service.ArticleService;
import com.ukj.exam.board.service.BoardService;
import com.ukj.exam.board.service.MemberService;
import com.ukj.exam.board.session.Session;
import com.ukj.exam.board.vo.Board;
import lombok.Getter;

import java.util.Scanner;

public class Container {
  @Getter
  private static Scanner sc;
  @Getter
  private static Session session;

  @Getter
  private static MemberService memberService;
  @Getter
  private static ArticleService articleService;
  @Getter
  private static BoardService boardService;

  @Getter
  private static MemberRepository memberRepository;
  @Getter
  private static BoardRepository boardRepository;
  @Getter
  private static ArticleRepository articleRepository;

  @Getter
  private static NeedLoginInterceptor needLoginInterceptor;
  @Getter
  private static NeedLogoutInterceptor needLogoutInterceptor;


  @Getter
  private static UsrArticleController usrArticleController;
  @Getter
  private static UsrMemberController usrMemberController;

  static {
    sc = new Scanner(System.in);
    session = new Session();

    memberService = new MemberService();
    boardService = new BoardService();
    articleService = new ArticleService();

    memberRepository = new MemberRepository();
    boardRepository = new BoardRepository();
    articleRepository = new ArticleRepository();

    needLoginInterceptor = new NeedLoginInterceptor();
    needLogoutInterceptor = new NeedLogoutInterceptor();

    usrArticleController = new UsrArticleController();
    usrMemberController = new UsrMemberController();

  }

  public static Session getSession() {
    return session;
  }
}
