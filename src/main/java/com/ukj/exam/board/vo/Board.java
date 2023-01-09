package com.ukj.exam.board.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Board {
  private int id;
  private String code; // 게시판을 코드로 분류
  private String name;
  private String regDate;
  private String updateDate;
}
