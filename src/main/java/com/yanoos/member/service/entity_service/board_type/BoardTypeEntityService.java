package com.yanoos.member.service.entity_service.board_type;

import com.yanoos.global.entity.board.BoardType;
import com.yanoos.member.repository.board_type.BoardTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardTypeEntityService {
    private final BoardTypeRepository boardTypeRepository;


    public BoardType getById(Long boardTypeId) {
        return boardTypeRepository.findById(boardTypeId).get();
    }
}
