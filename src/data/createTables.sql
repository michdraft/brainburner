-- Table exercisearea
create table exercisearea (
	id int primary key not null generated always as identity,
	areaname varchar(150)
);

-- Table pool
create table pool (
	id int primary key not null generated always as identity,
	exerciseareaid int references exercisearea(id) on delete cascade,
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
	exerciseareaid int references exercisearea(id) on delete cascade,
	userid int references users(id)
);

-- Table languages
create table languages (
	id int primary key not null generated always as identity,
	languagename varchar(75)
);

-- Table lang-table-relations
create table lang_table_rel (
	id int primary key not null generated always as identity,
	languageid int references languages(id),
	exerciseareaid int references exercisearea(id) on delete cascade
);

-- Table statistic
create table statistic (
	id int primary key not null generated always as identity,
	userid int references users(id),
	exerciseareaid int references exercisearea(id) on delete cascade,
	percent decimal,
	learndate bigint
);