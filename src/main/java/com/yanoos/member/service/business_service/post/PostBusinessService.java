package com.yanoos.member.service.business_service.post;

import com.yanoos.global.entity.board.Post;
import com.yanoos.global.exception.BusinessException;
import com.yanoos.global.exception.code.CommonErrorCode;
import com.yanoos.member.controller.dto.GetPostsOut;
import com.yanoos.member.controller.dto.MapMemberPostOut;
import com.yanoos.global.entity.member.MapMemberPost;
import com.yanoos.global.entity.member.Member;
import com.yanoos.member.service.entity_service.map_member_post.MapMemberPostEntityService;
import com.yanoos.member.service.entity_service.member.MemberEntityService;
import com.yanoos.member.service.entity_service.post.PostEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostBusinessService {
    private final MapMemberPostEntityService mapMemberPostEntityService;
    private final MemberEntityService memberEntityService;
    private final PostEntityService postEntityService;

    public GetPostsOut getMapMemberPostsByMemberId(Long memberId){
        Member member = memberEntityService.getMemberByMemberId(memberId);
        List<MapMemberPost> mapMemberPosts = mapMemberPostEntityService.getMapMemberPostsByMember(member);
        List<MapMemberPostOut> memberPostOuts = mapMemberPosts.stream().map(MapMemberPost::toDto).toList();
        return GetPostsOut.builder()
                .mapMemberPostOuts(memberPostOuts)
                .build();
    }

    @Transactional
    public void updateCheckedByMapMemberPostId(Long memberId, Long mapMemberPostId) {
        validationUpdateCheckedByMapMemberPostId(memberId, mapMemberPostId);
        MapMemberPost mapMemberPost = mapMemberPostEntityService.getByMapMemberPostId(mapMemberPostId);
        mapMemberPost.updateChecked();
    }

    private void validationUpdateCheckedByMapMemberPostId(Long memberId, Long mapMemberPostId) {
        MapMemberPost mapMemberPost = mapMemberPostEntityService.getByMapMemberPostId(mapMemberPostId);
        if(mapMemberPost.getMember().getId() != memberId){
            throw new BusinessException(CommonErrorCode.MEMBER_NOT_FOUND);
        }
    }

    public Post getPostByPostId(Long postId) {
        Post post = postEntityService.getPostByPostId(postId);
        return post;
    }
}
