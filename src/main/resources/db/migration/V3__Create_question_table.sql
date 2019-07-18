create table question
(
	id INTEGER auto_increment,
	title VARCHAR(50),
	description TEXT,
	gmt_create BIGINT,
	gmt_modified BIGINT,
	creator INTEGER,
	comment_count INTEGER default 0,
	view_count INTEGER default 0,
	like_count INTEGER default 0,
	tags VARCHAR(256),
	constraint question_pk
		primary key (id)
);