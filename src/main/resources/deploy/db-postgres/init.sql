-- "member" definition

-- Drop table

-- DROP TABLE "member";

CREATE TABLE "member" (
	member_id bigserial NOT NULL,
	member_email varchar(255) NOT NULL,
	member_nickname varchar(255) NOT NULL,
	member_role varchar(255) NOT NULL,
	telegram_authentication_uuid uuid NULL,
	telegram_authentication_uuid_created_at int8 NULL,
	is_approved bool DEFAULT false NOT NULL,
	CONSTRAINT member_member_email_key UNIQUE (member_email),
	CONSTRAINT member_member_nickname_key UNIQUE (member_nickname),
	CONSTRAINT member_pkey PRIMARY KEY (member_id),
	CONSTRAINT member_telegram_authentication_uuid_key UNIQUE (telegram_authentication_uuid)
);
CREATE INDEX idx_member_telegram_uuid ON member USING btree (telegram_authentication_uuid);

-- board_type definition

-- Drop table

-- DROP TABLE board_type;

CREATE TABLE board_type (
	board_type_id bigserial NOT NULL,
	board_type_name varchar(255) NOT NULL,
	board_type_description varchar(255) NULL,
	CONSTRAINT board_type_board_type_name_key UNIQUE (board_type_name),
	CONSTRAINT board_type_pkey PRIMARY KEY (board_type_id)
);

-- board definition

-- Drop table

-- DROP TABLE board;

CREATE TABLE board (
	board_id bigserial NOT NULL,
	board_name_eng varchar(255) NOT NULL,
	board_name_kor varchar(255) NOT NULL,
	board_class_name varchar(255) NOT NULL,
	board_url text NOT NULL,
	board_type_id int8 NOT NULL,
	site_url text NOT NULL,
	last_crawled_at timestamptz NULL,
	previous_crawled_at timestamptz NULL,
	CONSTRAINT board_board_url_key UNIQUE (board_url),
	CONSTRAINT board_pkey PRIMARY KEY (board_id)
);

CREATE TABLE error_log (
	error_log_id bigserial NOT NULL,
	topic varchar(255) NOT NULL,
	topic2 varchar(255) NULL,
	log text NULL,
	occurrence_time timestamptz NULL,
	CONSTRAINT error_log_pkey PRIMARY KEY (error_log_id)
);

-- map_member_board definition

-- Drop table

-- DROP TABLE map_member_board;

CREATE TABLE map_member_board (
	map_member_board_id bigserial NOT NULL,
	member_id int8 NOT NULL,
	board_id int8 NOT NULL,
	CONSTRAINT map_member_board_pkey PRIMARY KEY (map_member_board_id),
	CONSTRAINT uk_map_member_board_member_id_board_id UNIQUE (member_id, board_id)
);

-- keyword definition

-- Drop table

-- DROP TABLE keyword;

CREATE TABLE keyword (
	keyword_id bigserial NOT NULL,
	member_id int8 NOT NULL,
	board_type_id int8 NOT NULL,
	keyword varchar(255) NOT NULL,
	CONSTRAINT keyword_pkey PRIMARY KEY (keyword_id),
	CONSTRAINT uk_keyword_member_id_board_type_id_keyword UNIQUE (member_id, board_type_id, keyword)
);

-- post definition

-- Drop table

-- DROP TABLE post;

CREATE TABLE post (
	post_id bigserial NOT NULL,
	board_id int8 NOT NULL,
	post_no varchar(255) NOT NULL,
	post_title varchar(255) NOT NULL,
	post_content text NULL,
	post_department varchar(255) NULL,
	post_url text NULL,
	monitor_time timestamptz NULL,
	post_write_date timestamptz NULL,
	method text NULL,
	endpoint text NULL,
	parameters text NULL,
	CONSTRAINT post_pkey PRIMARY KEY (post_id),
	CONSTRAINT uk_post_board_id_post_no_post_title UNIQUE (board_id, post_no, post_title)
);
CREATE INDEX idx_post_no ON post USING btree (post_no);
CREATE INDEX idx_post_post_no_post_title_board_id ON post USING btree (post_no, post_title, board_id);
CREATE INDEX idx_post_title ON post USING btree (post_title);

-- map_member_post definition

-- Drop table

-- DROP TABLE map_member_post;

CREATE TABLE map_member_post (
	map_member_post_id bigserial NOT NULL,
	member_id int8 NOT NULL,
	post_id int8 NOT NULL,
	checked bool NOT NULL,
	keywords _text NOT NULL,
	CONSTRAINT map_member_post_pkey PRIMARY KEY (map_member_post_id),
	CONSTRAINT uk_map_member_post_member_id_post_id UNIQUE (member_id, post_id)
);

-- member_oauth definition

-- Drop table

-- DROP TABLE member_oauth;

CREATE TABLE member_oauth (
	member_oauth_id bigserial NOT NULL,
	member_id int8 NOT NULL,
	oauth_host varchar(255) NOT NULL,
	CONSTRAINT member_oauth_pkey PRIMARY KEY (member_oauth_id)
);

-- member_oauth_kakao definition

-- Drop table

-- DROP TABLE member_oauth_kakao;

CREATE TABLE member_oauth_kakao (
	member_oauth_kakao_id bigserial NOT NULL,
	member_oauth_id int8 NOT NULL,
	kakao_email varchar(255) NOT NULL,
	kakao_id varchar(255) NOT NULL,
	kakao_nickname varchar(255) NULL,
	CONSTRAINT member_oauth_kakao_kakao_email_key UNIQUE (kakao_email),
	CONSTRAINT member_oauth_kakao_kakao_id_key UNIQUE (kakao_id),
	CONSTRAINT member_oauth_kakao_pkey PRIMARY KEY (member_oauth_kakao_id)
);

-- map_member_telegram_user definition

-- Drop table

-- DROP TABLE map_member_telegram_user;

CREATE TABLE map_member_telegram_user (
	telegram_user_id bigserial NOT NULL,
	member_id int8 NOT NULL,
	CONSTRAINT map_member_telegram_user_pkey PRIMARY KEY (telegram_user_id)
);

-- "event" definition

-- Drop table

-- DROP TABLE "event";

CREATE TABLE "event" (
	event_id bigserial NOT NULL,
	parent_event_id int8 NULL,
	event_data text NOT NULL,
	published bool DEFAULT false NOT NULL,
	event_type varchar(255) NULL,
	try_count int8 NOT NULL,
	created_at timestamptz NULL,
	published_at timestamptz NULL,
	CONSTRAINT event_pkey PRIMARY KEY (event_id)
);


INSERT INTO board_type
(board_type_name, board_type_description)
VALUES('registered', '등록기관');