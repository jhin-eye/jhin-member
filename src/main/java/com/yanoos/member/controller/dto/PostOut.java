package com.yanoos.member.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostOut {
    private Long postId;               // post_id에 대응
    private BoardOut board;            // board_id에 대응
    private String postNo;             // post_no에 대응
    private String postTitle;          // post_title에 대응
    private ZonedDateTime postWriteDate; // post_write_date에 대응
    private String postDepartment;     // post_department에 대응
    private String postUrl;            // post_url에 대응
    private ZonedDateTime monitorTime; // monitor_time에 대응
    private String siteUrl;            // site_url에 대응

}
