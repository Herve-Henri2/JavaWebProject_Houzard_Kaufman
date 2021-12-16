drop Database if exists projectdb;
create DATABASE if not exists projectdb;
use projectdb;

drop table if exists user;
create table user
(
username varchar(45) primary key,
password varchar(45),
role varchar(15)
);

drop table if exists todo;
create table todo
(
id integer primary key,
description varchar(600)
);
SET @@global.time_zone = '+00:00';
SET @@session.time_zone = '+00:00';
insert into user value('Antoine', '123','student');
insert into user value('Marc', '321','teacher');
insert into user value('quentin', '','teacher');
insert into user value('t', '','teacher');
select * from user;

insert into todo value(1, 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa');
insert into todo value(2, 'bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb');

-- delete from todo where id = 1;

select * from todo;
select max(id) from todo;

update todo set description = 'cccc' where id = 1;








