-- Table exercisearea
create table exercisearea (
	id int primary key not null generated always as identity,
	areaname varchar(150)
);

-- Table pool
create table pool (
	id int primary key not null generated always as identity,
	exerciseareaid int references exercisearea(id),
	question varchar(200),
	answer varchar(200)
);

-- Table users
create table users (
	id int primary key not null generated always as identity,
	username varchar(50) not null,
	password varchar(32) not null
);

-- Table user-table-relations
create table user_table_rel (
	id int primary key not null generated always as identity,
	exerciseareaid int references exercisearea(id),
	userid int references users(id)
);

-- Table languages
create table languages (
	id int primary key not null generated always as identity,
<<<<<<< HEAD
	languagename varchar(75)
=======
	name varchar(75)
>>>>>>> 86fb77cd47954c0f83c042cb36771d4a71ef38ed
);

-- Table lang-table-relations
create table lang_table_rel (
	id int primary key not null generated always as identity,
	languageid int references languages(id),
	exerciseareaid int references exercisearea(id)
);