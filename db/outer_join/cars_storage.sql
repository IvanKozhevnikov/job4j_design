create table car_bodies(
    id serial primary key,
    name varchar(255)
);

create table car_engines(
    id serial primary key,
    name varchar(255)
);

create table car_transmissions(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('Crossover');
insert into car_bodies(name) values ('Sedan');
insert into car_bodies(name) values ('Hatchback');
insert into car_bodies(name) values ('Station Wagon');
insert into car_bodies(name) values ('Liftback');
insert into car_bodies(name) values ('Pickup truck');
insert into car_bodies(name) values ('Minivan');

insert into car_engines(name) values ('V4');
insert into car_engines(name) values ('V5');
insert into car_engines(name) values ('V6');
insert into car_engines(name) values ('V8');
insert into car_engines(name) values ('V10');
insert into car_engines(name) values ('V12');

insert into car_transmissions(name) values ('CVT');
insert into car_transmissions(name) values ('DSG');
insert into car_transmissions(name) values ('AT');
insert into car_transmissions(name) values ('MT');
insert into car_transmissions(name) values ('AMT');

insert into cars(name, body_id, engine_id, transmission_id) values ('Ford Mustang', 1, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) values ('Chevrolet Camaro', 2, 2, 2);
insert into cars(name, body_id, engine_id, transmission_id) values ('Maserati Quattroporte', 3, 3, 3);
insert into cars(name, body_id, engine_id, transmission_id) values ('GMC Canyon', 4, 4, 4);
insert into cars(name, body_id, engine_id, transmission_id) values ('Cadillac Escalade', 5, 5, 3);
insert into cars(name, body_id, engine_id, transmission_id) values ('Lada Kalina', null, null, null);

--Вывести список всех машин и все привязанные к ним детали
select c.id, c.name as car_name, cb.name as body_name, ce.name as engine_name, ct.name as transmission_name from cars as c left join car_bodies as cb on c.body_id = cb.id
left join car_engines as ce on c.engine_id = ce.id
left join car_transmissions as ct on c.transmission_id = ct.id;

--Вывести кузова, которые не используются НИ в одной машине
select cb.name as body_name from car_bodies cb left join cars c on cb.id = c.body_id where c.body_id is null;

--Вывести двигатели, которые не используются НИ в одной машине
select ce.name as engine_name from car_engines ce left join cars c on ce.id = c.engine_id where c.engine_id is null;

--Вывести коробки передач, которые не используются НИ в одной машине
select ct.name as transmission_name from car_transmissions ct left join cars c on ct.id = c.transmission_id where c.transmission_id is null;
