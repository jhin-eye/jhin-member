package com.yanoos.member.service.business_service.board;


import com.yanoos.global.entity.board.Board;
import com.yanoos.member.repository.board.BoardRepository;
import com.yanoos.member.service.entity_service.board.BoardEntityService;
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
public class BoardBusinessService {
    private final BoardEntityService boardEntityService;

    public List<Board> getAllBoards(Sort sort){
        List<Board> boards = boardEntityService.getAllBoards(Sort.by(Sort.Direction.ASC,"lastCrawledAt")
                .and(Sort.by(Sort.Direction.ASC,"previousCrawledAt")));

        return boards;

    }

}
