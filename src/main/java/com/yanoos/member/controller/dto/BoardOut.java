package com.yanoos.member.controller.dto;


import com.yanoos.global.entity.board.BoardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardOut {
    private Long id;

    private String nameEng;

    private String nameKor;

    private String url;

    private BoardType type;
    private String siteUrl;
}
