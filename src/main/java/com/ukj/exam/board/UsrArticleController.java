package com.ukj.exam.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsrArticleController {
  int articleLastId;
  List<Article> articles;

  UsrArticleController() {
    articleLastId = 0;
    articles = new ArrayList<>();

    makeTestData();

    if (articles.size() > 0) {
      articleLastId = articles.get(articles.size() - 1).id;
    }
  }

  void makeTestData() {
    for (int i = 1; i <= 100; i++) {
      articles.add(new Article(i, "제목" + i, "내용" + i));
    }
  }

  public void actionDelete(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if (id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
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

  public void actionModify(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if (id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
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

  public void actionWrite() {
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

  public void actionDetail(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if (id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
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

  public void showList(Rq rq) {
    Map<String, String> params = rq.getQueryParams();

    System.out.println("== 게시물 리스트 ==");
    System.out.println("-------------------");
    System.out.println("번호 / 제목");

    // 검색 시작
    List<Article> filteredArticles = articles;

    if (params.containsKey("searchKeyword")) {
      String searchKeyword = rq.getParam("searchKeyword", "");

      if (searchKeyword.length() > 0) {
        filteredArticles = new ArrayList<>();
      }

      for (Article article : articles) {
        boolean matched = article.title.contains(searchKeyword) || article.body.contains(searchKeyword);
        if (matched) {
          filteredArticles.add(article);
        }
      }
      // 검색 끝
    }

    List<Article> sortedArticles = filteredArticles;

    String orderBy = rq.getParam("orderBy", "idDesc");
    boolean orderByIdDesc = orderBy.equals("idDesc");

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
