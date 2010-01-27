-- Table users
create table users (
	id int primary key not null generated always as identity,
	username varchar(50) not null,
	password varchar(32) not null
);

-- Table statistic
create table statistic (
	id int primary key not null generated always as identity,
	result int,
	userid int references users(id)
);

-- Table languages
create table languages (
	id int primary key not null generated always as identity,
	name varchar(50)
);

-- Table user-table-relations
create table user_table_rel (
	id int primary key not null generated always as identity,
	learntable int,
	userid int references users(id)
);

-- Table lang-table-relations
create table lang_table_rel (
	id int primary key not null generated always as identity,
	language int references languages(id),
	name varchar(50)
);

-- Template for user-tables
create table example_table (
	id int primary key not null generated always as identity,
	question varchar(100),
	answer varchar(100)
);
