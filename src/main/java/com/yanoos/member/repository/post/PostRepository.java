package com.yanoos.member.repository.post;

import com.yanoos.member.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
    public Optional<Post> findByPostId(Long postId);

}
