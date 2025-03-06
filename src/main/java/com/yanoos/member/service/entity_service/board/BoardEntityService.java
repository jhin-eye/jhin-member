package com.yanoos.member.service.entity_service.board;


import com.yanoos.global.entity.board.Board;
import com.yanoos.member.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardEntityService {
    private final BoardRepository boardRepository;

    public List<Board> getAllBoards(Sort sort){
        return boardRepository.findAll(sort);
    }

    public Board getBoardById(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("Board not found"));
    }
}
