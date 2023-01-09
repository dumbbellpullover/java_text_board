package com.ukj.exam.board.repository;

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

  public int write(int boardId, int memberId, String title, String body) {
    int id = lastId + 1;
    Article article = new Article(id, boardId, memberId, title, body);
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

}
