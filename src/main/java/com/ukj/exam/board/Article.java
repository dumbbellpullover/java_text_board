package com.ukj.exam.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString // @Data 만 써도 상관없음
class Article {
  int id;
  String title;
  String body;

}
