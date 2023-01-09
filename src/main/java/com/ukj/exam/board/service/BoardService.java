package com.ukj.exam.board.service;

import com.ukj.exam.board.container.Container;
import com.ukj.exam.board.repository.BoardRepository;
import com.ukj.exam.board.vo.Board;

public class BoardService {
  private BoardRepository boardRepository;

  public BoardService() {
    boardRepository = Container.getBoardRepository();
  }
  public Board getBoardById(int boardId) {
    return boardRepository.getBoardById(boardId);
  }
}
