package com.ukj.exam.board.service;

import com.ukj.exam.board.container.Container;
import com.ukj.exam.board.repository.BoardRepository;
import com.ukj.exam.board.vo.Board;

public class BoardService {
  private BoardRepository boardRepository;

  public BoardService() {
    boardRepository = Container.getBoardRepository(); // containor 로 가져와야
  }
  public Board getBoardById(int boardId) {
    return boardRepository.getBoardById(boardId);
  }

  public void makeTestData() {
    make("notice", "공지사항");
    make("free", "자유");

  }

  private int make(String code, String name) {
    return boardRepository.make(code, name);
  }
}
