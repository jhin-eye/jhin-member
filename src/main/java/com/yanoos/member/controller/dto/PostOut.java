package com.yanoos.member.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostOut {
    private Long postId;               // post_id에 대응
    private String boardNameEng;       // board_name_eng에 대응
    private String boardNameKor;       // board_name_kor에 대응
    private String postNo;             // post_no에 대응
    private String postTitle;          // post_title에 대응
    private LocalDateTime postWriteDate; // post_write_date에 대응
    private String postDepartment;     // post_department에 대응
    private String postUrl;            // post_url에 대응
    private LocalDateTime monitorTime; // monitor_time에 대응

}
