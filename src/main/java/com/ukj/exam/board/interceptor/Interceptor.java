package com.ukj.exam.board.interceptor;

import com.ukj.exam.board.vo.Rq;

public interface Interceptor {
  boolean run(Rq rq);
}
