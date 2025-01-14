CREATE TABLE "member" (
	member_id bigserial primary key,
	member_email varchar(255) UNIQUE NOT NULL,
	member_nickname varchar(255) NOT NULL,
	member_role varchar(255) not null,
	telegram_authentication_uuid uuid UNIQUE,
	telegram_authentication_uuid_created_at BIGINT NULL,
	is_approved boolean not null default false
);
CREATE INDEX idx_member_telegram_uuid ON member USING btree (telegram_authentication_uuid);

create table board_type(
	board_type_id bigserial primary key,
	board_type_name varchar(255) unique not null,
	board_type_description varchar(255) null
);

create table board(
	board_id bigserial primary key,
	board_name_eng varchar(255) not null,
	board_name_kor varchar(255) not null,
	board_class_name varchar(255) not null,
	board_url text unique not null,
	site_url text unique not null,
	board_type_id bigint not null,
	last_crawled_at TIMESTAMPTZ,
	previous_crawled_at TIMESTAMPTZ,
	base_path_url text, --사이트 주소
	page_button_xpath text, --페이지 버튼 영역
	page_move_success_check_xpath text, --페이지 이동 성공 여부 체크용 
	list_tag varchar(255), --table인지 ul인지
	content_in_detail_xpath text, -- 상세조회 내부의 content의 xpath
	col_no_index number,
	col_title_index number,
	col_detail_button_index number,
	col_write_date_index number,
	col_department_index number,
	col_url_index number,
	need_detail_title boolean

);

create table map_member_board(
	map_member_board_id bigserial primary key,
	member_id bigint not null,
	board_id bigint not null,
	constraint uk_map_member_board_member_id_board_id unique (member_id,board_id)
);

CREATE TABLE keyword (
	keyword_id bigserial primary key,
	member_id bigint not null,
	board_type_id bigint not null,
	keyword varchar(255) not null,
	constraint uk_keyword_member_id_board_type_id_keyword unique (member_id, board_type_id, keyword)
);

CREATE TABLE post (
	post_id bigserial primary key,
	board_id bigint not null,
	post_no varchar(255) NOT NULL,
	post_title varchar(255) NOT NULL,
	post_content text,
	post_write_date TIMESTAMPTZ,
	post_department varchar(255),
	post_url text,
	monitor_time TIMESTAMPTZ DEFAULT now() NOT NULL,
	constraint uk_post_board_id_post_no_post_title unique (board_id, post_no, post_title)
);
CREATE INDEX idx_post_no ON post (post_no);
CREATE INDEX idx_post_title ON post (post_title);

CREATE TABLE map_member_post (
    map_member_post_id bigserial PRIMARY KEY, 
    member_id bigint NOT NULL,
    post_id bigint NOT NULL,
    checked boolean NOT NULL,
    keywords text[] NOT NULL,
    CONSTRAINT uk_map_member_post_member_id_post_id UNIQUE (member_id, post_id)
);

CREATE TABLE member_oauth (
	member_oauth_id bigserial primary key,
	member_id bigint NOT NULL,
	oauth_host varchar(255) NOT NULL
);

CREATE TABLE member_oauth_kakao (
	member_oauth_kakao_id bigserial primary key,
	member_oauth_id bigint NOT NULL,
	kakao_email varchar(255) unique NOT NULL,
	kakao_id varchar(255) unique NOT NULL,
	kakao_nickname varchar(255)
);

CREATE TABLE map_member_telegram_user (
	telegram_user_id bigserial primary key,
	member_id bigint NOT NULL
);

CREATE TABLE "event" (
	event_id bigserial primary key,
	parent_event_id bigint,
	event_data text NOT NULL,
	published bool DEFAULT false NOT NULL,
	event_type varchar(255) NULL,
	try_count bigint not null,
	created_at TIMESTAMPTZ DEFAULT now() NOT NULL,
	published_at TIMESTAMPTZ
);

create index idx_post_post_no_post_title_board_id on Post(post_no, post_title, board_id);


