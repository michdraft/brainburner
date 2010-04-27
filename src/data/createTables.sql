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


# OvervieExTable Views
create view overviewextable as (
	select POOL.QUESTION, POOL.ANSWER, EXERCISEAREA.AREANAME from EXERCISEAREA join POOL
	on EXERCISEAREA.ID = POOL.EXERCISEAREAID
);

# OverviewTable Views
create view overviewtableAreanameId as (
	select EXERCISEAREA.AREANAME, EXERCISEAREA.ID, USER_TABLE_REL.USERID
	from USER_TABLE_REL join EXERCISEAREA on USER_TABLE_REL.EXERCISEAREAID = EXERCISEAREA.ID
);

create view overviewtableLanguagename as (
	select LANGUAGES.LANGUAGENAME, LANG_TABLE_REL.EXERCISEAREAID
	from LANG_TABLE_REL join LANGUAGES on LANG_TABLE_REL.LANGUAGEID = LANGUAGES.ID
);

create view exerciseareaFromUser as (
	select EXERCISEAREA.AREANAME, EXERCISEAREA.ID, USER_TABLE_REL.USERID
	from USER_TABLE_REL join EXERCISEAREA on USER_TABLE_REL.EXERCISEAREAID = EXERCISEAREA.ID
);