package com.yanoos.member.repository.map_member_keyword;


import com.yanoos.member.entity.member.MapMemberKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapMemberKeywordRepository extends JpaRepository<MapMemberKeyword, Long>, MapMemberKeywordRepositoryCustom {

}
