package com.yanoos.member.controller.test;

import com.yanoos.global.jwt.CustomPrincipal;
import com.yanoos.member.controller.test.dto.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public ResponseEntity<GetTestOutDTO> getTest(){
        log.info("get Test");
        return ResponseEntity.ok(GetTestOutDTO.builder().result("ok").build());
    }

    //숨고용 임시 api
    @PostMapping
    public ResponseEntity<PostTestOutDTO> postTest(@RequestBody PostTestInDTO postTestInDTO){
        log.info("input is {}",postTestInDTO);
        return ResponseEntity.ok(PostTestOutDTO.builder()
                .boardName(postTestInDTO.getBoardName())
                .boardUrl(postTestInDTO.getBoardUrl())
                .postTitle(postTestInDTO.getPostTitle())
                .postUrl(postTestInDTO.getPostUrl())
                .build());
    }

    @PostMapping("/valid")
    public ResponseEntity<ValidTestOutDTO> validTest(@RequestBody @Valid ValidTestInDTO validTestInDTO){
        return ResponseEntity.ok(ValidTestOutDTO.builder().p1("11").build());
    }
    @GetMapping("/api/user/info")
    @ResponseBody
    public Long validJwt(){
        log.info("validJwt in");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            CustomPrincipal principal = (CustomPrincipal) authentication.getPrincipal();
            // principal에서 userId, groupId, personaId, authName 등에 접근 가능
            return principal.getMemberId();
        }
        return -1L;
    }

}
