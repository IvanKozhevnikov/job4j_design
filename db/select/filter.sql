create table product(
id serial primary key,
name varchar(255),
type_id integer references type(id),
expired_date date,
price integer
)

create table type(
id integer primary key,
name varchar(255));

insert into type(id, name) values(1, 'сыр');
insert into type(id, name) values(2, 'мороженное');
insert into type(id, name) values(3, 'молоко');
insert into product(name, type_id , expired_date, price) values('Адыгейский сыр', 1, '2023-07-10', 150);
insert into product(name, type_id , expired_date, price) values('Белебеевский сыр', 1, '2023-08-12', 200);
insert into product(name, type_id , expired_date, price) values('сыр Ламбер', 1, '2023-08-15', 250);
insert into product(name, type_id , expired_date, price) values('сыр Брест-Литовск', 1, '2020-04-10', 210);
insert into product(name, type_id , expired_date, price) values('сыр Viola', 1, '2021-02-23', 190);
insert into product(name, type_id , expired_date, price) values('сыр Пармезан', 1, '2023-09-03', 180);
insert into product(name, type_id , expired_date, price) values('сыр Hohland', 1, '2024-06-18', 260);
insert into product(name, type_id , expired_date, price) values('мороженное Коровка из Кореновки', 2, '2015-05-04', 90);
insert into product(name, type_id , expired_date, price) values('мороженное 48 Копеек', 2, '2025-07-17', 48);
insert into product(name, type_id , expired_date, price) values('мороженное Snikers', 2, '2020-09-14', 110);
insert into product(name, type_id , expired_date, price) values('мороженное Mars', 2, '2024-01-10', 120);
insert into product(name, type_id , expired_date, price) values('мороженное Milka', 2, '2023-03-12', 80);
insert into product(name, type_id , expired_date, price) values('мороженное Чистая Линия', 2, '2017-11-24', 60);
insert into product(name, type_id , expired_date, price) values('мороженное Twix', 2, '2023-07-25', 105);
insert into product(name, type_id , expired_date, price) values('молоко Домик в деревне', 3, '2016-08-21', 76);
insert into product(name, type_id , expired_date, price) values('молоко Пискарёвское', 3, '2014-07-30', 87);
insert into product(name, type_id , expired_date, price) values('молоко Простоквашино', 3, '2025-03-22', 79);
insert into product(name, type_id , expired_date, price) values('молоко Большая кружка', 3, '2023-08-15', 99);
insert into product(name, type_id , expired_date, price) values('молоко Parmalat', 3, '2038-06-23', 101);
insert into product(name, type_id , expired_date, price) values('молоко Весёлый молочник', 3, '2018-09-16', 92);
insert into product(name, type_id , expired_date, price) values('молоко Село Зелёное', 3, '2028-09-26', 84);
insert into product(name, type_id , expired_date, price) values('молоко Белый город', 3, '2030-01-10', 93);
insert into product(name, type_id , expired_date, price) values('молоко Наше дело', 3, '2030-01-11', 260);

select p.name from product as p inner join type as t on p.type_id=t.id where t.name='сыр';
select p.name from product as p where p.name like '%мороженное%';
select p.name from product as p where p.expired_date <'2023-04-07';
select p.name from product as p where p.price in(select MAX(p.price) from product as p);
select t.name, count(p.name) as count from product as p inner join type as t ON t.id = p.type_id group by t.name;
select p.name from product as p inner join type as t on p.type_id=t.id where t.name in ('сыр', 'молоко');
select t.name from product as p inner join type as t on p.type_id=t.id group by t.name having count(p.name) < 10;
select p.name, t.name as type from product as p inner join type as t ON t.id = p.type_id;
