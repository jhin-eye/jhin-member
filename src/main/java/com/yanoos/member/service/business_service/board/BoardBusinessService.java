package com.yanoos.member.service.business_service.board;


import com.yanoos.global.entity.board.Board;
import com.yanoos.global.entity.board.Post;
import com.yanoos.member.controller.dto.GetPostsOut;
import com.yanoos.member.service.entity_service.board.BoardEntityService;
import com.yanoos.member.service.entity_service.post.PostEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
    private final PostEntityService postEntityService;
    public List<Board> getAllBoards(Sort sort){
        List<Board> boards = boardEntityService.getAllBoards(Sort.by(Sort.Direction.ASC,"lastCrawledAt")
                .and(Sort.by(Sort.Direction.ASC,"previousCrawledAt")));

        return boards;

    }

    public GetPostsOut getBoardPostsByBoardId(Long boardId, int page, int size) {
        Page<Post> posts = postEntityService.getPostsByBoardId(boardId, page, size);

        return GetPostsOut.builder()
                .postOuts(posts.getContent().stream().map(Post::toDto).toList())
                .totalElements(posts.getTotalElements())
                .totalPages(posts.getTotalPages())
                .currentPage(posts.getNumber())
                .build();

    }

    public Board getBoardByBoardId(Long boardId) {
        return boardEntityService.getBoardById(boardId);
    }
}
