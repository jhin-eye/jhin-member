package com.yanoos.member.controller.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yanoos.global.entity.board.Post;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
@Slf4j
public class GetPostDetailSiteOut {
    private Long id;               // post_id에 대응
    private BoardOut board;            // board_id에 대응
    private String no;             // post_no에 대응
    private String title;          // post_title에 대응
    private ZonedDateTime writeDate; // post_write_date에 대응
    private String department;     // post_department에 대응
    private String url;            // post_url에 대응
    private ZonedDateTime monitorTime; // monitor_time에 대응
    private String siteUrl;            // site_url에 대응
    private String method;
    private String endpoint;
    private Map<String,String> parameterMap;

    public GetPostDetailSiteOut from(Post post) throws JsonProcessingException {
        this.id = post.getId();
        this.board = post.getBoard().toDto();
        this.no = post.getNo();
        this.title = post.getTitle();
        this.writeDate = post.getWriteDate();
        this.department = post.getDepartment();
        this.url = post.getUrl();
        this.monitorTime = post.getMonitorTime();
        this.method = post.getMethod();
        this.endpoint = post.getEndpoint();
        // JSON 문자열 처리
        ObjectMapper objectMapper = new ObjectMapper();
        if (post.getParameters() != null && !post.getParameters().isEmpty()) {
            // JSON 문자열을 Map으로 변환
            this.parameterMap = objectMapper.readValue(post.getParameters(), new TypeReference<Map<String, String>>() {});
        } else {
            this.parameterMap = new HashMap<>(); // 빈 Map으로 처리
        }
        return this;
    }
}
