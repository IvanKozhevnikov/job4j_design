create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Нокиа 3310', 4000), ('Сони эриксон т100', 4500), ('Самсунг r210s', 7000);
insert into people(name) values ('Иван'), ('Евгения'), ('Александр'), ('Сергей'), ('Михаил'), ('Константин');

insert into devices_people(device_id, people_id) values (1, 1), (1, 2), (1, 5), (1, 6);
insert into devices_people(device_id, people_id) values (2, 2), (2, 3), (2, 4), (2, 6);
insert into devices_people(device_id, people_id) values (3, 1), (3, 3); (3, 5), (3, 6);


select avg(price) from devices;

select p.name, avg(d.price)
from devices_people as dp
left join devices d
on dp.device_id = d.id
inner join people p
on dp.people_id = p.id
group by p.name;

select p.name, avg(d.price)
from devices_people as dp
left join devices d
on dp.device_id = d.id
inner join people p
on dp.people_id = p.id
group by p.name
having avg(d.price) > 5000;
