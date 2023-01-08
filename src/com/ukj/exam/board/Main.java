package com.ukj.exam.board;

import java.util.*;

public class Main {
  static void makeTestData(List<Article> articles) {
    for (int i = 1; i <= 100; i++) {
      articles.add(new Article(i, "제목" + i, "내용" + i));
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int articleLastId = 0;

    List<Article> articles = new ArrayList();

    makeTestData(articles);

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
        actionUsrArticleList(rq, articles);

      } else if (rq.getUrlPath().equals("/usr/article/detail")) { // 상세정보 출력
        actionUsrArticleDetail(rq, articles);

      } else if (rq.getUrlPath().equals("/usr/article/write")) {
//        make /usr/article/write fucntion
        actionUsrArticleWrite(sc, articles, articleLastId);
        articleLastId++;

      } else {
        System.out.printf("입력 된 명령어: %s\n", cmd);
      }
    }

    System.out.println("== 프로그램 종료 ==");
    sc.close();
  }

  private static void actionUsrArticleWrite(Scanner sc, List<Article> articles, int articleLastId) {
    System.out.println("== 게시물 등록 ==");
    System.out.print("제목: ");
    String title = sc.nextLine();
    System.out.print("내용: ");
    String body = sc.nextLine();
    int id = ++articleLastId;

    Article article = new Article(id, title, body);
    articles.add(article);

    System.out.println("생성된 게시물 객체: " + article);
    System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);
  }

  private static void actionUsrArticleDetail(Rq rq, List<Article> articles) {
    Map<String, String> params = rq.getQueryParams();

    if (!params.containsKey("id")) {
      System.out.println("id를 입력해주세요.");
      return;
    }

    int id = 0;
    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수형태로 입력해주세요.");
      return;
    }

    Article article = articles.get(id - 1);

    if (id > articles.size()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    System.out.println("== 게시물 상세 보기 ==");
    System.out.printf("번호: %d\n", article.id);
    System.out.printf("제목: %s\n", article.title);
    System.out.printf("내용: %s\n", article.body);


  }

  private static void actionUsrArticleList(Rq rq, List<Article> articles) {
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

class Article {
  int id;
  String title;
  String body;

  Article(int id, String title, String body) {
    this.id = id;
    this.title = title;
    this.body = body;
  }

  @Override
  public String toString() {
    return String.format("{id: %d, title: \"%s\", body: \"%s\"}", this.id, this.title, this.body);
  }
}

class Rq {
  String url;
  Map<String, String> queryParams;
  String urlPath;

  Rq(String url) {
    this.url = url;
    this.queryParams = Util.getQueryParamsFromUrl(this.url);
    this.urlPath = Util.getUrlPathFromUrl(this.url);
  }

  public Map<String, String> getQueryParams() {
    return this.queryParams;
  }

  public String getUrlPath() {
    return this.urlPath;
  }
}

class Util {
  static Map<String, String> getQueryParamsFromUrl(String url) {
    Map<String, String> queryParams = new HashMap();
    String[] urlBits = url.split("\\?", 2);

    if (urlBits.length == 1) {
      return queryParams;
    }

    for (String queryString : urlBits[1].split("&", 2)) {
      String[] params = queryString.split("=", 2);

      if (params.length == 1) {
        continue;
      }

      queryParams.put(params[0], params[1]);
    }

    return queryParams;
  }

  static String getUrlPathFromUrl(String url) {
    return url.split("\\?", 2)[0];
  }

  public static <T> List<T> reverseList(List<T> list) {
    List<T> reverse = new ArrayList<>(list.size());

    for (int i = list.size() - 1; i >= 0; i--) {
      reverse.add(list.get(i));
    }
    return reverse;
  }
}