package com.ukj.exam.board;

import java.util.*;

public class Main {
  static int articleLastId = 0;
  static List<Article> articles = new ArrayList<>();

  static void makeTestData() {
    for (int i = 1; i <= 100; i++) {
      articles.add(new Article(i, "제목" + i, "내용" + i));
    }
  }

  public static void main(String[] args) {
    Scanner sc = Container.sc;

    makeTestData();

    if (articles.size() > 0) {
      articleLastId = articles.get(articles.size() - 1).id;
    }

    System.out.println("== 게시판 v 0.1 ==");
    System.out.println("== 프로그램 시작 ==");

    while (true) {
      System.out.print("명령 > ");
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);
      Map<String, String> params = rq.getQueryParams();

      if (rq.getUrlPath().equals("exit")) {
        break;

      } else if (rq.getUrlPath().equals("/usr/article/list")) { // 리스트 출력
        actionUsrArticleList(rq);

      } else if (rq.getUrlPath().equals("/usr/article/detail")) { // 상세정보 출력
        actionUsrArticleDetail(rq);

      } else if (rq.getUrlPath().equals("/usr/article/write")) { // 게시물 입력
        actionUsrArticleWrite();

      } else if (rq.getUrlPath().equals("/usr/article/modify")) { // 게시물 수정
        actionUsrArticleModify(rq);

      } else if (rq.getUrlPath().equals("/usr/article/delete")) { // 게시물 삭제
        actionUsrArticleDelete(rq);

      } else {
        System.out.printf("입력 된 명령어: %s\n", cmd);
      }
    }

    System.out.println("== 프로그램 종료 ==");
    sc.close();
  }

  private static void actionUsrArticleDelete(Rq rq) {
    Map<String, String> params = rq.getQueryParams();

    if (!params.containsKey("id")) {
      System.out.println("id를 입력해주세요.");
      return;
    }

    int id;
    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수형태로 입력해주세요.");
      return;
    }

    Article foundArticle = null;

    for (Article article : articles) {
      if (article.id == id) {
        foundArticle = article;
        break;
      } else if (article.id != id) {
        System.out.println("해당 게시물은 존재하지 않습니다.");
        return;
      }
    }

    articles.remove(articles.remove(foundArticle));
    System.out.printf("%d번 게시물을 삭제하였습니다.\n", id);
  }

  private static void actionUsrArticleModify(Rq rq) {
    Map<String, String> params = rq.getQueryParams();

    if (!params.containsKey("id")) {
      System.out.println("id를 입력해주세요.");
      return;
    }

    int id;
    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수형태로 입력해주세요.");
      return;
    }

    if (id > articles.size()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = articles.get(id - 1);

    System.out.printf("새 제목: ");
    article.title = Container.sc.nextLine();
    System.out.printf("새 내용: ");
    article.body = Container.sc.nextLine();

    System.out.printf("%d번 게시물을 수정하였습니다.\n", article.id);

  }

  private static void actionUsrArticleWrite() {
    System.out.println("== 게시물 등록 ==");
    System.out.print("제목: ");
    String title = Container.sc.nextLine();
    System.out.print("내용: ");
    String body = Container.sc.nextLine();

    int id = ++articleLastId;

    Article article = new Article(id, title, body);
    articles.add(article);

    System.out.println("생성된 게시물 객체: " + article);
    System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);
  }

  private static void actionUsrArticleDetail(Rq rq) {
    Map<String, String> params = rq.getQueryParams();

    if (!params.containsKey("id")) {
      System.out.println("id를 입력해주세요.");
      return;
    }

    int id;
    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수형태로 입력해주세요.");
      return;
    }

    if (id > articles.size()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = articles.get(id - 1);

    System.out.println("== 게시물 상세 보기 ==");
    System.out.printf("번호: %d\n", article.id);
    System.out.printf("제목: %s\n", article.title);
    System.out.printf("내용: %s\n", article.body);


  }

  private static void actionUsrArticleList(Rq rq) {
    Map<String, String> params = rq.getQueryParams();

    System.out.println("== 게시물 리스트 ==");
    System.out.println("-------------------");
    System.out.println("번호 / 제목");

    // 검색 시작
    List<Article> filteredArticles = articles;

    if (params.containsKey("searchKeyword")) {
      String searchKeyword = params.get("searchKeyword");
      filteredArticles = new ArrayList<>();

      for (Article article : articles) {
        boolean matched = article.title.contains(searchKeyword) || article.body.contains(searchKeyword);
        if (matched) {
          filteredArticles.add(article);
        }
      }
      // 검색 끝
    }

    List<Article> sortedArticles = filteredArticles;

    boolean orderByIdDesc = true;
    if (params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")) {
      orderByIdDesc = false;
    }

    if (orderByIdDesc) {
      sortedArticles = Util.reverseList(sortedArticles);
    }

    for (Article article : sortedArticles) {
      System.out.printf("%d / %s\n", article.id, article.title);
    }

    System.out.println("-------------------");

  }
}