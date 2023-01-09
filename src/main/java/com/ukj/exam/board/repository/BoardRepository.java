package com.ukj.exam.board.repository;

import com.ukj.exam.board.vo.Article;
import com.ukj.exam.board.vo.Board;

import java.util.ArrayList;
import java.util.List;

public class BoardRepository {
  private List<Board> boards;
  private int lastId;

  public BoardRepository() {
    this.boards = new ArrayList();
    this.lastId = 0;
  }

  public Board getBoardById(int boardId) {
    for (Board board : boards) {
      if (board.getId() == boardId) {
        return board;
      }
    }

    return null;
  }

}
