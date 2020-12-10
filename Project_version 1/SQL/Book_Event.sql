create table book_event(
	no int auto_increment not null primary key,
	name varchar(100) not null,
	img_cover varchar(255) not null,
	img_contents varchar(255) not null,
	state boolean default false not null,
	date DATETIME default CURRENT_TIMESTAMP not null
);