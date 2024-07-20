package com.yanoos.member.repository.map_member_keyword;


import com.yanoos.member.entity.MapMemberKeyword;
import com.yanoos.member.entity.dto.MapMemberKeywordId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MapMemberKeywordRepository extends JpaRepository<MapMemberKeyword, MapMemberKeywordId> {

}
