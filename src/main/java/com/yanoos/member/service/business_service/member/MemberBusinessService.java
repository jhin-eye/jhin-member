package com.yanoos.member.service.business_service.member;

import com.yanoos.global.kafka.dto.PostCreatedIn;
import com.yanoos.member.controller.member.GenerateTelegramUuidOut;
import com.yanoos.global.entity.board.Post;
import com.yanoos.global.entity.member.Keyword;
import com.yanoos.global.entity.member.MapMemberPost;
import com.yanoos.global.entity.member.Member;
import com.yanoos.member.service.entity_service.map_member_post.MapMemberPostEntityService;
import com.yanoos.member.service.entity_service.member.MemberEntityService;
import com.yanoos.member.service.entity_service.post.PostEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberBusinessService {
    private final MemberEntityService memberEntityService;
    private final PostEntityService postEntityService;
    private final MapMemberPostEntityService mapMemberPostEntityService;
    /**
     * 텔레그램 UUID를 생성한다.
     *
     * @param memberId
     * @return
     */
    @Transactional
    public GenerateTelegramUuidOut generateTelegramUuid(Long memberId) {
        UUID uuid = UUID.randomUUID();
        memberEntityService.updateTelegramUuid(memberId, uuid);
        return GenerateTelegramUuidOut.builder()
                .memberId(memberId)
                .uuid(uuid)
                .build();
    }


    public Member getMemberById(Long memberId) {
        Member member = memberEntityService.getMemberByMemberId(memberId);
        Hibernate.initialize(member.getKeywords());
        Hibernate.initialize(member.getMapMemberTelegramUsers());
        return member;
    }

    @Transactional
    public Map<Member, List<String>> mapMembersWithPost(PostCreatedIn postCreatedIn) {
        Post post = postEntityService.getPostByPostId(postCreatedIn.getValue().getPostId());
        log.info("postCreatedIn: {}", postCreatedIn.toString());
        List<Member> members = memberEntityService.getAllMembers();
        Map<Member, List<String>> mapMemberContainKeywords = createMapMemberContainKeywords(members, post);
        for (Member member : mapMemberContainKeywords.keySet()) {
            List<String> keywords = mapMemberContainKeywords.get(member);
            MapMemberPost mapMemberPost = MapMemberPost.builder()
                    .member(member)
                    .post(post)
                    .keywords(keywords)
                    .build();
            mapMemberPostEntityService.saveMapMemberPost(mapMemberPost);
            member.addMapMemberPost(mapMemberPost);
            post.addMapMemberPost(mapMemberPost);
        }
        //연관있는 멤버들만 리스트로
        return mapMemberContainKeywords;
    }

    private Map<Member, List<String>> createMapMemberContainKeywords(List<Member> members, Post post) {
        Map<Member, List<String>> mapMemberContainKeywords = new HashMap<>();
        members.forEach(member -> {
            List<String> containedKeywords = member.getKeywords().stream()
                    .map(Keyword::getKeyword) // 각 MapMemberKeyword에서 키워드를 추출
                    .filter(keyword -> post.getTitle().contains(keyword)) // 추출된 키워드가 postTitle에 포함되어 있는지 필터링
                    .toList(); // 필터된 키워드를 리스트로 변환
            if(containedKeywords.isEmpty()) return;
            mapMemberContainKeywords.put(member, containedKeywords);
        });
        return mapMemberContainKeywords;
    }
}
