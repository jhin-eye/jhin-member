package com.yanoos.member.repository.post;

import com.yanoos.global.entity.board.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

    Page<Post> findByBoardIdOrderByIdDesc(Long boardId, Pageable pageable);
}
