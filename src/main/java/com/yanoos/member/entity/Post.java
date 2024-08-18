package com.yanoos.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "board_name_eng", nullable = false, length = 255)
    private String boardNameEng;

    @Column(name = "board_name_kor", nullable = false, length = 255)
    private String boardNameKor;

    @Column(name = "post_no", nullable = false, length = 255)
    private String postNo;

    @Column(name = "post_title", nullable = false, length = 255)
    private String postTitle;

    @Column(name = "post_write_date")
    private LocalDateTime postWriteDate;

    @Column(name = "post_department", nullable = false, columnDefinition = "text")
    private String postDepartment;

    @Column(name = "post_url", nullable = false, columnDefinition = "text")
    private String postUrl;

    @Column(name = "monitor_time", columnDefinition = "timestamptz default CURRENT_TIMESTAMP")
    private LocalDateTime monitorTime;
}