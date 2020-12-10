create table book_member(
	no int auto_increment not null primary key,
	name varchar(10) not null,
	id varchar(20) not null,
	pw varchar(255) not null,
	birth char(10) not null,
	email varchar(100) not null,
	phone char(13) not null,
	point int default 0,
	postcode char(5),
	address varchar(150),
	address_detail varchar(150)
);