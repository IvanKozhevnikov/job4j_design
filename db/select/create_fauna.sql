create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('Giant Land Turtle', 150, '1832-09-01');
insert into fauna(name, avg_age, discovery_date) values ('Psychedelic Clown', 15, '2008-07-03');
insert into fauna(name, avg_age, discovery_date) values ('Deep Sea Ramari', 50, '2011-03-09');
insert into fauna(name, avg_age, discovery_date) values ('Raccoon Olinguito', 50, '2006-11-25');
insert into fauna(name, avg_age, discovery_date) values ('Philippine Monitor lizard', 70, '2010-12-15');
insert into fauna(name, avg_age, discovery_date) values ('Sad monkey', 15000, '2012-05-14');
insert into fauna(name, avg_age, discovery_date) values ('Giant flying couscous', 40, null);
insert into fauna(name, avg_age, discovery_date) values ('Long fish', 40, '2017-06-19');

select * from fauna where avg_age between 10000 and 21000;
select * from fauna where name like '%fish%';
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';
