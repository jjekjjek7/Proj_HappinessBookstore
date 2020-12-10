create table book_cart(
	no int auto_increment not null primary key,
	title varchar(255) not null,
	author varchar(150) not null,
	cover varchar(255) null,
	price int not null,
	point int default 0 not null,
	cnt int not null,
	member_no int null,
	isbn char(13) not null,
	FOREIGN KEY (member_no) REFERENCES book_member (no) ON DELETE CASCADE
);