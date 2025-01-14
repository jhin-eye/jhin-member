package com.yanoos.member.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
public class GetPostsOut {
    private List<MapMemberPostOut> mapMemberPostOuts;
    private int totalPages;
    private int currentPage;
    private long totalElements;

}
