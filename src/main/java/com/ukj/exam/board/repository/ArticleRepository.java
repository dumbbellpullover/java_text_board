package com.ukj.exam.board.repository;

import com.ukj.exam.board.util.Util;
import com.ukj.exam.board.vo.Article;
import com.ukj.exam.board.vo.Board;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
  private int lastId;
  private List<Article> articles;

  public ArticleRepository() {
    this.lastId = 0;
    this.articles = new ArrayList();
  }

  public List<Article> getArticles() {
    return articles;
  }

  public List<Article> getArticles(int boardId, String searchKeyword, String searchKeywordTypeCode, String orderBy) {

    if ( boardId == 0 && searchKeyword.length() == 0 ) {
      return articles;
    }

    List<Article> filteredArticles = new ArrayList<>();

    if (searchKeyword.length() > 0) {
      filteredArticles = new ArrayList<>();

      for (Article article : articles) {
        boolean matched = article.getTitle().contains(searchKeyword) || article.getBody().contains(searchKeyword);

        if (matched) {
          filteredArticles.add(article);
        }
      }
    }

    for (Article article : articles) {
      if (boardId != 0) {
        if (article.getBoardId() != boardId) {
          continue;
        }
      }

      if (searchKeyword.length() > 0) {
        switch (searchKeywordTypeCode) {
          case "body":
            if (!article.getBody().contains(searchKeyword)) {
              continue;
            }
          case "title,body":
            if (!article.getBody().contains(searchKeyword) && !article.getTitle().contains(searchKeyword)) {
              continue;
            }
          case "title":
            if (!article.getTitle().contains(searchKeyword)) {
              continue;
            }
          default:
            break;
        }
      }

    }

    boolean orderByIdDesc = false;

    if (orderBy.equals("idDesc")) {
      orderByIdDesc = true;
    }

    if (orderByIdDesc) {
      filteredArticles = Util.reverseList(filteredArticles);
    }

    return filteredArticles;
  }

  public int write(int boardId, int memberId, String title, String body) {
    int id = lastId + 1;
    String regDate = Util.getNowDateStr();
    String updateDate = regDate;
    Article article = new Article(id, boardId, memberId, title, body, regDate, updateDate);
    articles.add(article);
    lastId = id;

    return id;
  }

  public Article getArticleById(int id) {
    for (Article article : articles) {
      if (article.getId() == id) {
        return article;
      }
    }

    return null;
  }

  public void deleteArticleById(int id) {
    Article article = getArticleById(id);

    if (article != null) {
      articles.remove(article);
    }
  }

  public void modify(int id, String title, String body) {
    Article article = getArticleById(id);

    article.setTitle(title);
    article.setBody(body);
    article.setUpdateDate(Util.getNowDateStr());
  }
}
