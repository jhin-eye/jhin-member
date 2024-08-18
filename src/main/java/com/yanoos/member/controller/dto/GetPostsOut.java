package com.yanoos.member.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetPostsOut {
    private List<AsideMenu> asideMenus;
    private String title;
    private List<PostOut> posts;
}
