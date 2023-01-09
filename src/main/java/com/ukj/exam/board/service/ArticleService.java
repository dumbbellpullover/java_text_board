package com.ukj.exam.board.service;

import com.ukj.exam.board.repository.ArticleRepository;
import com.ukj.exam.board.util.Util;
import com.ukj.exam.board.vo.Article;

import java.util.List;

public class ArticleService {

  private ArticleRepository articleRepository;

  public ArticleService() {
    articleRepository = new ArticleRepository(); // new 로 꼭 만들어야
  }

  public void makeTestData() {
    for (int i = 1; i <= 10; i++) {
      String title = "제목" + i;
      String body = "내용" + i;
      write(1, 1, title, body);
    }
  }

  public int write(int boardId, int memberId, String title, String body) {
    return articleRepository.write(boardId, memberId, title, body);
  }

  public List<Article> getArticles() {
    return articleRepository.getArticles();
  }

  public Article getArticleById(int id) {
    return articleRepository.getArticleById(id);
  }

  public void deleteArticleById(int id) {
    articleRepository.deleteArticleById(id);
  }

  public void modify(int id, String title, String body) {
    articleRepository.modify(id, title, body);
  }
}
