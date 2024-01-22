create table member(
                       member_id bigserial,
                       member_email varchar(255) unique not null,
                       member_nickname varchar(255) unique not null,
                       primary key (member_id)
);

create table oauth_member(
                           oauth_id bigserial,
                           member_id bigint not null,
                           oauth_host varchar(255) not null,
                           primary key (oauth_id)
);

create table kakao_member
(
                           oauth_id bigint,
                           kakao_id varchar(255) unique not null,
                           kakao_email varchar(255) unique not null,
                           kakao_nickname varchar(255) unique not null,
                           primary key (oauth_id)
);

alter table oauth_member
    add constraint fk_oauth_user_member
        foreign key (member_id)
            references member (member_id);


alter table kakao_member
    add constraint fk_kakao_user_oauth_user
        foreign key (oauth_id)
            references oauth_member (oauth_id);