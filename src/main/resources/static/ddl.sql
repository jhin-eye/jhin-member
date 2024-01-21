create table member(
                       member_id bigserial,
                       member_email varchar(255) unique not null,
                       member_nickname varchar(255) unique not null,
                       primary key (member_id)
);


create table kakao_user(
                           kakao_id varchar(255),
                           kakao_email varchar(255) unique not null,
                           kakao_nickname varchar(255) unique not null,
                           member_id bigint ,
                           primary key (kakao_id)
);


alter table kakao_user
    add constraint fk_kakao_user_member
        foreign key (member_id)
            references member (member_id);