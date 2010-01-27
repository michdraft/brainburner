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
	user int references users(id)
);

-- Table languages
create table languages (
	id int primary key not null generated always as identity,
	name varchar(50)
);

-- Table user-table-relation
create table user_table_relation (
	id int primary key not null generated always as identity,
	table int,
	user int references users(id)
);

-- Template for user-tables
create table example_table (
	id int primary key not null generated always as identity,
	language int references languages(id)
	question varchar(100),
	answer varchar(100)
);
