package com.ukj.exam.board.repository;

import com.ukj.exam.board.util.Util;
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

  public int make(String code, String name) {
    int id = lastId + 1;
    String regDate = Util.getNowDateStr();
    String updateDate = regDate;
    Board board = new Board(id, code, name, regDate, updateDate);
    boards.add(board);
    lastId = id;

    return id;
  }
}
