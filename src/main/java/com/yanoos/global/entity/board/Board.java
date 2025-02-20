package com.yanoos.global.entity.board;

import com.yanoos.member.controller.dto.BoardOut;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.ZonedDateTime;

/*

create table board(
	board_id bigserial primary key,
	board_name_eng varchar(255) not null,
	board_name_kor varchar(255) not null,
	board_url text unique not null,
	board_type_id bigint not null,
	constraint fk_board_type_id foreign key(board_type_id) references board_type(board_type_id)
	ON DELETE CASCADE
	ON UPDATE CASCADE
);
 */
@Entity
@Getter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "board_name_eng", nullable = false, length = 255)
    private String nameEng;

    @Column(name = "board_name_kor", nullable = false, length = 255)
    private String nameKor;

    @Column(name = "board_url", nullable = false, columnDefinition = "text")
    private String url;

    @ManyToOne
    @JoinColumn(name = "board_type_id", nullable = false)
    private BoardType type;

    @Column(name = "site_url", nullable = false, columnDefinition = "text")
    private String siteUrl;
    @Column(name = "last_crawled_at")
    private ZonedDateTime lastCrawledAt;
    @Column(name = "previous_crawled_at")
    private ZonedDateTime previousCrawledAt;

    public BoardOut toDto() {
        return BoardOut.builder()
                .id(this.id)
                .nameEng(this.nameEng)
                .nameKor(this.nameKor)
                .url(this.url)
                .type(this.type)
                .siteUrl(this.siteUrl)
                .build();
    }
}
