create table book_qna(
	no int auto_increment not null primary key,
	name varchar(10) not null,
	id varchar(20) not null,
	member_no int,
	type varchar(50) not null,
	title varchar(100) not null,
	contents text not null,
	date DATETIME default CURRENT_TIMESTAMP not null,
	state boolean default 0 not null,
	comment text,
	FOREIGN KEY (member_no) REFERENCES book_member (no) ON DELETE CASCADE
);