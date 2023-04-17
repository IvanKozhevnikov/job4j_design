create table person (
    id serial primary key,
    name varchar(50)
);

insert into person (name) values ('Сергей Иванов');
insert into person (name) values ('Виктор Петров');
insert into person (name) values ('Степан Васильев');
insert into person (name) values ('Василий Сидоров');

create table engine (
    id serial primary key,
    name varchar(50)
);

insert into engine (name) values ('V2');
insert into engine (name) values ('V6');
insert into engine (name) values ('V4');
insert into engine (name) values ('V5');

create table car (
    id serial primary key,
    name varchar(50),
    engine_id int references engine(id)
);

insert into car (name, engine_id) values ('Ford', 1);
insert into car (name, engine_id) values ('BMW', 1);
insert into car (name, engine_id) values ('Opel', 2);
insert into car (name, engine_id) values ('Reno', 1);
insert into car (name, engine_id) values ('Skoda', 1);

create table ownership (
    id serial primary key,
    active boolean default true,
    person_id integer references person(id),
    engine_id integer references engine(id),
    car_id integer references car(id)
);

insert into ownership (car_id, person_id) values (3, 1);
insert into ownership (car_id, person_id) values (4, 1);
insert into ownership (car_id, person_id) values (2, 2);
insert into ownership (car_id, person_id) values (2, 3);
insert into ownership (car_id, person_id) values (2, 3);
insert into ownership (car_id, person_id) values (5, 2);
insert into ownership (car_id, person_id) values (4, 2);
insert into ownership (car_id, person_id) values (1, 2);

create view show_persons_with_3_or_more_engines_V2
    as select p.name as person, count(e.name), e.name as engine from person as p
         join ownership o on p.id = o.person_id
         join car c on o.car_id = c.id
         join engine e on c.engine_id = e.id
         group by (p.name, e.name) having count(e.name) >= 3;
