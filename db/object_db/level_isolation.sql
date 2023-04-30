create table cars (
    id serial primary key,
    brand varchar(50),
    model varchar(50),
    count_of_machine integer default 0,
    price integer
);

insert into cars (brand, model, count_of_machine, price) VALUES ('Muscovite', '412', 48, 7000);
insert into cars (brand, model, count_of_machine, price) VALUES ('Lada', '2106', 19, 9000);
insert into cars (brand, model, count_of_machine, price) VALUES ('Volga', 'GAZ-21', 23, 11000);


--Скрипт для проведения трнзакций Read Committed


--Первая транзакция
begin transaction;
select * from cars;
insert into cars (brand, model, count_of_machine, price) VALUES ('Volga', 'GAZ-21', 35, 11000);
delete from cars where price = 9000;
update cars set price = 7000 where brand = 'Muscovite';
select * from cars;
commit;

--Вторая транзакция
begin transaction;
select * from cars;
select * from cars;



--Скрипт для проведения трнзакций level serializable



--Первая транзакция
begin transaction isolation level serializable;
select sum(count_of_machine) from cars;
update cars set count_of_machine = 30 where brand = 'Lada';
commit;

--Вторая транзакция
begin transaction isolation level serializable;
select sum(count_of_machine) from cars;
update cars set count_of_machine = 47 where brand = 'Muscovite';
commit;


--Скрипт для проведения трнзакций level repeatable read



--Первая транзакция
begin transaction isolation level repeatable read;
select * from cars;
insert into cars (brand, model, count_of_machine, price) VALUES ('Volga', 'GAZ-21', 35, 11000);
delete from cars where price = 9000;
update cars set price = 7000 where brand = 'Muscovite';
commit;

--Вторая транзакция
begin transaction isolation level repeatable read;
select * from cars;
update cars set price = 7000 where brand = 'Muscovite';

--Первая транзакция
begin transaction isolation level repeatable read;
select * from cars;
insert into cars (brand, model, count_of_machine, price) VALUES ('Volga', 'GAZ-21', 35, 11000);
delete from cars where price = 9000;
update cars set price = 7000 where brand = 'Muscovite';
commit;
rollback

--Вторая транзакция
begin transaction isolation level repeatable read;
select * from cars;
update cars set price = 7000 where brand = 'Muscovite';
select * from cars;