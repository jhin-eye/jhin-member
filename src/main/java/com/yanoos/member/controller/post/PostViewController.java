package com.yanoos.member.controller.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/view/posts")
public class PostViewController {
    @GetMapping()
    public String getPosts(Model model){

        model.addAttribute("title","게시글 목록");
        model.addAttribute("contentTemplate","pages/content/posts::section");
        model.addAttribute("asideTemplate","pages/aside/postAside::aside");
        return "layout";
    }
}
