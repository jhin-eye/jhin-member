package com.yanoos.member.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
public class MapMemberPostOut {
    private Long mapMemberPostId;
    private MemberOut memberOut;
    private PostOut postOut;
    private boolean checked;
}
