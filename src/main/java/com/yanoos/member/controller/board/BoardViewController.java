package com.yanoos.member.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/view/boards")
public class BoardViewController {
    @GetMapping
    public String getAllBoardsStatus(){
        return "board/boards";
    }

    /**
     * 멤버의 게시판만 ㅂ여줌
     * 추후 업뎃예정
     * @return
     */
    @GetMapping("/{memberId}")
    public String getMemberBoardsStatus(){
        return "temp";
    }
}
