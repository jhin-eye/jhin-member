package com.yanoos.member.repository.board;

import com.yanoos.global.entity.board.Board;
import com.yanoos.global.entity.board.BoardType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Override
    List<Board> findAll(Sort sort);
}
