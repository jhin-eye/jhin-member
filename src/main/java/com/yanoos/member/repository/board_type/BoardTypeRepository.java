package com.yanoos.member.repository.board_type;

import com.yanoos.global.entity.board.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardTypeRepository extends JpaRepository<BoardType, Long> {
    BoardType findByName(String name);
}
