package com.ukj.exam.board;

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
    String loginId = this.title != null ? "\"" + this.title + "\"" : null;
    String loginPw = this.body != null ? "\"" + this.body + "\"" : null;

    return String.format("{id: %d, title: %s, body: %s}", this.id, this.title, this.body);
  }
}
