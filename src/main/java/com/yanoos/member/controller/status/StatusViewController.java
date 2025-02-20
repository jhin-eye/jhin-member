package com.yanoos.member.controller.status;

import com.yanoos.global.entity.board.Board;
import com.yanoos.member.service.business_service.board.BoardBusinessService;
import com.yanoos.member.service.entity_service.board.BoardEntityService;
import com.yanoos.member.service.entity_service.board_type.BoardTypeEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/view/status")
public class StatusViewController {
    private final BoardEntityService boardEntityService;
    private final BoardBusinessService boardBusinessService;

    @GetMapping
    public String getStatus(Model model){

        List<Board> boards = boardBusinessService.getAllBoards(Sort.by(Sort.Direction.ASC,"lastCrawledAt")
                .and(Sort.by(Sort.Direction.ASC,"previousCrawledAt")));
        model.addAttribute("boards",boards);
        log.info("boards = {}",boards);

        return "status/status";
    }

}
