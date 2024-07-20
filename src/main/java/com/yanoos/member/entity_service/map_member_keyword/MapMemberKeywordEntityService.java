package com.yanoos.member.entity_service.map_member_keyword;

import com.yanoos.member.entity.MapMemberKeyword;
import com.yanoos.member.entity_service.map_member_keyword.dto.CreateMapMemberKeywordIn;
import com.yanoos.member.repository.map_member_keyword.MapMemberKeywordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MapMemberKeywordEntityService {
    private final MapMemberKeywordRepository mapmemberKeywordRepository;


    @Transactional
    public MapMemberKeyword createMapMemberKeyword(CreateMapMemberKeywordIn createMapMemberKeywordIn) {
        MapMemberKeyword build = MapMemberKeyword.builder()
                .keyword(createMapMemberKeywordIn.getKeyword())
                .member(createMapMemberKeywordIn.getMember())
                .build();
        return mapmemberKeywordRepository.save(build);
    }


}
