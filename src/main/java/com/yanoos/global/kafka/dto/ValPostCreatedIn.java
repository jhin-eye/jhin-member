package com.yanoos.global.kafka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class ValPostCreatedIn {
    @JsonProperty("post_id")
    private Long postId;
    @JsonProperty("board_name_eng")
    private String boardNameEng;
    @JsonProperty("board_name_kor")
    private String boardNameKor;
    @JsonProperty("post_no")
    private String postNo;
    @JsonProperty("post_title")
    private String postTitle;
    @JsonProperty("post_url")
    private String postUrl;
    @JsonProperty("post_write_date")
    private String postWriteDate;

//    @JsonCreator
//    public ValNewPostIn(
//            @JsonProperty("post_id") Long postId,
//            @JsonProperty("board_name_eng") String boardNameEng,
//            @JsonProperty("board_name_kor") String boardNameKor,
//            @JsonProperty("post_no") String postNo,
//            @JsonProperty("post_title") String postTitle,
//            @JsonProperty("post_url") String postUrl,
//            @JsonProperty("post_write_date") String postWriteDate) {
//        this.postId = postId;
//        this.boardNameEng = boardNameEng;
//        this.boardNameKor = boardNameKor;
//        this.postNo = postNo;
//        this.postTitle = postTitle;
//        this.postUrl = postUrl;
//        this.postWriteDate = postWriteDate;
//    }
}
