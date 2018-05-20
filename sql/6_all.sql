drop database if exists testing;

create database if not exists testing default charset=UTF8;

create user 'dzmitry'@'localhost' identified by '777';
grant select, insert, update, delete on testing.* to ''@'localhost';

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


insert into users(id, login, password, email, first_name, last_name) values
(1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin@gmail.com', 'admin_fn', 'admin_ln'),
(2, 'tutor', '1f6f42334e1709a4e0f9922ad789912b', 'tutor@gmail.com', 'tutor_fn', 'tutor_ln'),
(3, 'student', 'cd73502828457d15655bbd7a63fb0bc8', 'student@gmail.com', 'student_fn', 'student_ln'),
(4, 'user', 'ee11cbb19052e40b07aac0ca060c23ee', 'user@gmail.com', 'user_fn', 'user_ln');

insert into role(role_name) values
('Admin'),
('Tutor'),
('Student'),
('User');

insert into user_role(user_id, role_id) values
(1, 1),
(1, 4),
(2, 2),
(2, 4),
(3, 3),
(3, 4),
(4, 4);

insert into subject(subject_name) values
('Информатика');

insert into test(id, test_name, subject_id, user_id) values
(1, 'Информатика - наше всё!', 1, 2);

insert into question(id, question_name, test_id) values
(1, 'Сведения об объектах окружающего нас мира это:', 1),
(2, 'Информацию, изложенную на доступном для получателя языке называют:', 1),
(3, 'Наибольший объем информации человек получает при помощи:', 1),
(4, 'Двоичный код каждого символа при кодировании текстовой информации (в кодах ASCII) занимает в памяти персонального компьютера:', 1),
(5, 'Измерение температуры представляет собой', 1),
(6, 'Что такое 1 байт?', 1),
(7, 'Алфавит азбуки Морзе состоит:', 1),
(8, 'Считая, что каждый символ кодируется одним байтом, определите, чему равен информационный объем следующего высказывания Жан-Жака Руссо: Тысячи путей ведут к заблуждению, к истине – только один.', 1),
(9, 'В кодировке Unicode на каждый символ отводится два байта. Определите информационный объем слова из двадцати четырех символов в этой кодировке.', 1),
(10, 'Метеорологическая станция ведет наблюдение за влажностью воздуха. Результатом одного измерения является целое число от 0 до 100 процентов, которое записывается при помощи минимально возможного количества бит. Станция сделала 80 измерений. Определите информационный объем результатов наблюдений.', 1);

insert into answer(id, answer_name, correct_answer, question_id) values
(1, 'информация', 1, 1),
(2, 'объект', 0, 1),
(3, 'предмет', 0, 1),
(4, 'информатика', 0, 1),
(5, 'понятной', 1, 2),
(6, 'полной', 0, 2),
(7, 'полезной', 0, 2),
(8, 'актуальной', 0, 2),
(9, 'органов слуха', 0, 3),
(10, 'органов зрения', 1, 3),
(11, 'органов обоняния', 0, 3),
(12, 'органов осязания', 0, 3),
(13, '1 байт', 1, 4),
(14, '1 Кб', 0, 4),
(15, '2 байта', 0, 4),
(16, '1 бит', 0, 4),
(17, 'процесс хранения', 0, 5),
(18, 'процесс передачи', 0, 5),
(19, 'процесс получения', 1, 5),
(20, 'процесс защиты', 0, 5),
(21, '1024 Кбайт', 0, 6),
(22, '4 бит', 0, 6),
(23, '8 бит', 1, 6),
(24, '10 Мбайт', 0, 6),
(25, 'нулей и единиц', 0, 7),
(26, 'из точек и тире', 1, 7),
(27, 'из 10 различных знаков', 0, 7),
(28, 'из одного знака', 0, 7),
(29, '92 бита', 0, 8),
(30, '220 бит', 0, 8),
(31, '456 бит', 1, 8),
(32, '512 бит', 0, 8),
(33, '384 бита', 1, 9),
(34, '192 бита', 0, 9),
(35, '256 бит', 0, 9),
(36, '48 бит', 0, 9),
(37, '80 бит', 0, 10),
(38, '70 байт', 1, 10),
(39, '80 байт', 0, 10),
(40, '560 байт', 0, 10);

insert into statistics(id, test_id, count_correct_answers, count_incorrect_answers, start_testing, finish_testing, user_id) values
(1, 1, 5, 5, '2017-03-15 12:00:00', '2017-03-15 13:00:00', 3),
(2, 1, 6, 4, '2017-03-15 14:00:00', '2017-03-15 15:00:00', 3),
(3, 1, 7, 3, '2017-03-15 16:00:00', '2017-03-15 17:00:00', 3);
