package com.yanoos.member.entity.board;

import jakarta.persistence.*;

/*
create table board_type(
	board_type_id bigserial primary key,
	board_type_name varchar(255) unique not null,
	board_type_description varchar(255) null
);
 */
@Entity
public class BoardType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_type_id")
    private Long id;

    @Column(name = "board_type_name", unique = true, nullable = false)
    private String name;

    @Column(name = "board_type_description")
    private String description;

}
