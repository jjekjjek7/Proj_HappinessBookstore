create table book_order(
	no int auto_increment not null primary key,
	date DATETIME default CURRENT_TIMESTAMP not null,
	title varchar(50) not null,
	payment_type varchar(20) not null,
	card varchar(50),
	installment int not null default 0,
	member_no int,
	name varchar(50) not null,
	phone varchar(50) not null,
	email varchar(255) not null,
	state varchar(50) not null,
	price int not null,
	point int default 0 not null,
	postcode char(5),
	address varchar(150),
	address_detail varchar(150),
	FOREIGN KEY (member_no) REFERENCES book_member (no) ON DELETE CASCADE
);