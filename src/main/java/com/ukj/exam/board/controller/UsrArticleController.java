package com.ukj.exam.board.controller;

import com.ukj.exam.board.service.ArticleService;
import com.ukj.exam.board.service.BoardService;
import com.ukj.exam.board.service.MemberService;
import com.ukj.exam.board.util.Util;
import com.ukj.exam.board.vo.Article;
import com.ukj.exam.board.container.Container;
import com.ukj.exam.board.vo.Board;
import com.ukj.exam.board.vo.Rq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsrArticleController {
  private ArticleService articleService;
  private BoardService boardService;
  private MemberService memberService;
  private List<Article> articles;

  public UsrArticleController() {
    boardService = Container.getBoardService();
    articleService  = Container.getArticleService();
    memberService = Container.getMemberService();

    makeTestData();
  }

  public void makeTestData() {
      boardService.makeTestData();
      articleService.makeTestData();
    }

  public void actionDelete(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if (id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    Article foundArticle = articleService.getArticleById(id);

    if (foundArticle == null) {
      System.out.println("해당 게시물은 존재하지 않습니다.");
      return;
    }

    articleService.deleteArticleById(id);

    System.out.printf("%d번 게시물을 삭제하였습니다.\n", id);
  }

  public void actionModify(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if (id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    Article foundArticle = articleService.getArticleById(id);

    if (foundArticle == null) {
      System.out.println("해당 게시물은 존재하지 않습니다.");
      return;
    }

    System.out.printf("새 제목: ");
    String title = Container.getSc().nextLine();
    System.out.printf("새 내용: ");
    String body = Container.getSc().nextLine();


    articleService.modify(foundArticle.getId(), title, body);

    System.out.printf("%d번 게시물을 수정하였습니다.\n", foundArticle.getId());

  }

  public void actionWrite(Rq rq) {

    int boardId = rq.getIntParam("boardId", 0);

    if (boardId == 0) {
      System.out.println("boardId를 올바르게 입력해주세요.");
      return;
    }

    Board foundBoard = boardService.getBoardById(boardId);

    if (foundBoard == null) {
      System.out.println("존재하지 않는 게시판입니다.");
      return;
    }

    System.out.printf("== %s 게시판 글 작성 ==\n", foundBoard.getName());
    System.out.print("제목: ");
    String title = Container.getSc().nextLine();
    System.out.print("내용: ");
    String body = Container.getSc().nextLine();

    int loggedMemberId = rq.getLoggedMemberID();

    int id = articleService.write(foundBoard.getId(), loggedMemberId, title, body, 0);

    System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
  }

  public void actionDetail(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if (id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    Article article = articleService.getArticleById(id);

    if (article == null) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    articleService.increaseHitCount(id);

    System.out.println("== 게시물 상세 보기 ==");
    System.out.printf("번호: %d\n", article.getId());
    System.out.printf("작성날짜: %s\n", article.getRegDate());
    System.out.printf("수정날짜: %s\n", article.getUpdateDate());
    System.out.printf("조회수: %d\n", article.getHitCount());
    System.out.printf("제목: %s\n", article.getTitle());
    System.out.printf("내용: %s\n", article.getBody());


  }

  public void showList(Rq rq) {
    int page = rq.getIntParam("page", 1);
    int pageItemCount = 10;
    int boardId = rq.getIntParam("boardId", 0);
    String searchKeyword = rq.getParam("searchKeyword", "");
    String searchKeywordTypeCode = rq.getParam("searchKeywordTypeCode", "");
    String orderBy = rq.getParam("orderBy", "idDesc");
    Board board = null;

    if (boardId != 0) {
      board = boardService.getBoardById(boardId);
    }

    if (board == null && boardId > 0) {
      System.out.println("해당 게시판 번호는 존재하지 않습니다.");
      return;
    }

    List<Article> filteredArticles = articleService.getArticles(boardId, searchKeyword, searchKeywordTypeCode, orderBy, page, pageItemCount);

    String boardName = board == null ? "전체" : board.getName();

    System.out.printf("== %s 게시물 리스트(%d건) ==\n", boardName, filteredArticles.size());
    System.out.println("-------------------");
    System.out.println("번호 / 게시판 / 작성자 / 제목 / 수정 날짜 및 시간 / 조회수");

    for (Article article : filteredArticles) {
      String articleBoardName = getBoardNameByBoardId(article.getBoardId());
      String writeName = getWriteNameByBoardId(article.getBoardId());

      System.out.printf("%d / %s / %s / %s / %s / %d\n", article.getId(), articleBoardName, writeName, article.getTitle(), article.getUpdateDate(), article.getHitCount());
    }

    System.out.println("-------------------");
  }

  private String getWriteNameByBoardId(int memberId) {
    return memberService.getMemberById(memberId).getLoginId();
  }

  private String getBoardNameByBoardId(int boardId) {
    return boardService.getBoardById(boardId).getName();
  }

}
