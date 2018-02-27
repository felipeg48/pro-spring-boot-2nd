create table person (
	id varchar(255) not null,
	email varchar(255) not null unique,
	name varchar(255) not null, 
	phone varchar(255),
	birthday date,
	primary key (id)
);


