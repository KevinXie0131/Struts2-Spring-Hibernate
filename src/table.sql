create table tuser(
	id varchar(36) primary key not null,
	name varchar(100) unique not null,
	pwd varchar(32),
	CREATEDATETIME date,
	MODIFYDATETIME date
);

create table tmenu(
	id varchar(36) primary key not null,
	pid varchar(36) CONSTRAINT fk_column_pid REFERENCES tmenu(id),
	text varchar(100),
	ICONCLS varchar(50),
	URL varchar(200)
);