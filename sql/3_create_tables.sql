use testing;

create table users
(
	id integer not null auto_increment,
	login varchar(32) unique not null,
	password varchar(128) not null,
	email varchar(32) unique not null,
    first_name varchar(64) not null,
    last_name varchar(64) not null,
    primary key (id)
);

create table role
(
	id integer not null auto_increment,
	role_name varchar(64) unique not null,
	primary key (id)
);

create table user_role
(
	user_id integer not null,
	role_id integer not null
);

create table test
(
    id integer not null auto_increment,
    test_name varchar(64) not null,
	subject_id integer not null,
    user_id integer not null,
    primary key (id)
);

create table subject
(
	id integer not null auto_increment,
	subject_name varchar(64) unique not null,
	primary key (id)
);

create table question
(
	id integer not null auto_increment,
    question_name text not null,
	test_id integer not null,
    primary key (id)
);

create table answer
(
	id integer not null auto_increment,
    answer_name varchar(256) not null,
    correct_answer boolean not null,
	question_id integer not null,
    primary key (id)
);

create table statistics
(
	id integer not null auto_increment,
	test_id integer not null,
    count_correct_answers integer not null,
    count_incorrect_answers integer not null,
    start_testing timestamp default current_timestamp,
	finish_testing timestamp default current_timestamp,
	user_id integer not null,
    primary key (id)
);

alter table user_role
add foreign key (user_id) references users(id) on update cascade on delete cascade,
add foreign key (role_id) references role(id) on update cascade on delete cascade;

alter table test
add foreign key (subject_id) references subject(id) on update cascade on delete cascade,
add foreign key (user_id) references users(id) on update cascade on delete cascade;

alter table question
add foreign key (test_id) references test(id) on update cascade on delete cascade;

alter table answer
add foreign key (question_id) references question(id) on update cascade on delete cascade;

alter table statistics
add foreign key (test_id) references test(id) on update cascade on delete cascade,
add foreign key (user_id) references users(id) on update cascade on delete cascade;
