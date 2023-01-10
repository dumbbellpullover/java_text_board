package com.ukj.exam.board.repository;

import com.ukj.exam.board.util.Util;
import com.ukj.exam.board.vo.Article;

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

  public List<Article> getArticles(int boardId, String searchKeyword, String searchKeywordTypeCode, String orderBy, int limitStart, int limitCount) {

    List<Article> filteredArticles = new ArrayList<>();

    for (Article article : articles) {
      if (article.getBoardId() == boardId) {
        filteredArticles.add(article);
      }
    }

    int dataIndex = 0;

    List<Article> sortedArticles = articles;

    boolean orderByIdDesc = orderBy.equals("idDesc");

    if (orderByIdDesc) {
      sortedArticles = Util.reverseList(sortedArticles);
    }

    for ( Article article : sortedArticles ) {
      if (boardId != 0) {
        if (article.getBoardId() != boardId) {
          continue;
        }
      }

      if (dataIndex >= limitStart) {
        filteredArticles.add(article);
      }

      dataIndex++;

      if (filteredArticles.size() == limitCount) {
        break;
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

    return filteredArticles;
  }

  public int write(int boardId, int memberId, String title, String body, int hitCount) {
    int id = lastId + 1;
    String regDate = Util.getNowDateStr();
    String updateDate = regDate;
    Article article = new Article(id, boardId, memberId, title, body, regDate, updateDate, hitCount);
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

  public void increaseHitCount(int id) {
    getArticleById(id).setHitCount(getArticleById(id).getHitCount() + 1);
  }
}
