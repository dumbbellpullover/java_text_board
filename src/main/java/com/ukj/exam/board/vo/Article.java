package com.ukj.exam.board.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
//@ToString
public class Article {
  private int id;
  private int boardId;
  private int memberId;
  private String title;
  private String body;
  private String regDate;
  private String updateDate;
  private int hitCount;

}
