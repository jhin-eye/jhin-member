package com.yanoos.global.entity.board;

import com.yanoos.global.entity.member.MapMemberPost;
import com.yanoos.member.controller.dto.PostOut;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post",
        uniqueConstraints = @UniqueConstraint(columnNames = {"board_id", "post_no", "post_title"}))
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(name = "post_no", nullable = false, length = 255)
    private String no;

    @Column(name = "post_title", nullable = false, length = 255)
    private String title;

    @Column(name = "post_content", columnDefinition = "text")
    private String content;

    @Column(name = "post_write_date")
    private ZonedDateTime writeDate;

    @Column(name = "post_department", nullable = false, length = 255)
    private String department;

    @Column(name = "post_url", nullable = false, columnDefinition = "text")
    private String url;

    @Column(name = "monitor_time", nullable = false)
    private ZonedDateTime monitorTime;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MapMemberPost> mapMemberPosts = new ArrayList<>();

    @Column(name = "method", nullable = false, columnDefinition = "text")
    private String method;
    @Column(name = "endpoint", nullable = false, columnDefinition = "text")
    private String endpoint;
    @Column(name = "parameters", nullable = false, columnDefinition = "text")
    private String parameters;


    public PostOut toDto(){
        return PostOut.builder()
                .postId(this.id)
                .board(this.board.toDto())
                .postNo(this.no)
                .postTitle(this.title)
                .postWriteDate(this.writeDate)
                .postDepartment(this.department)
                .postUrl(this.url)
                .monitorTime(this.monitorTime)
                .build();
    }

    public void addMapMemberPost(MapMemberPost mapMemberPost) {
        this.mapMemberPosts.add(mapMemberPost);
    }
}