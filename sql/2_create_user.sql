use testing;

create user 'dzmitry'@'localhost' identified by '777';
grant select, insert, update, delete on testing.* to ''@'localhost';
