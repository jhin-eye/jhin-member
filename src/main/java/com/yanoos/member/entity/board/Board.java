package com.yanoos.member.entity.board;

import jakarta.persistence.*;

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

}
