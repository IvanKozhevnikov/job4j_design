/* many-to-many */
 
 create table drivers(
     id serial primary key,
     name varchar(255)
 );
 
 create table cars(
     id serial primary key,
     name varchar(255)
 );
 
 create table cars_drivers(
     id serial primary key,
     driver_id int references drivers(id),
     car_id int references cars(id)
 );
insert into drivers(name) values ('Fedor');
insert into drivers(name) values ('Alexander');
insert into drivers(name) values ('Sergey');
insert into drivers(name) values ('Michael');

insert into cars(name) values ('Volga');
insert into cars(name) values ('Oka');
insert into cars(name) values ('Muscovite');
insert into cars(name) values ('Izh');

insert into cars_drivers(driver_id, car_id) values (1, 2);
insert into cars_drivers(driver_id, car_id) values (1, 3);
insert into cars_drivers(driver_id, car_id) values (2, 4);
insert into cars_drivers(driver_id, car_id) values (2, 1);
insert into cars_drivers(driver_id, car_id) values (3, 3);
insert into cars_drivers(driver_id, car_id) values (4, 1);

select * from cars where id in (select car_id from cars_drivers);
select * from drivers where id in (select driver_id from cars_drivers);

/* one-to-one */
create table country(
    country_name varchar(255) primary key,
    capital varchar(255),
    inhabitants int,
    area int   
);

create table capital(
    country_name varchar(255) primary key,
    capital_name varchar(255),
    inhabitants int,
    area int,
    country_id varchar(255) references country(country_name) unique
);

insert into country(country_name, capital, inhabitants, area) values ('Australia', 'Canberra', 28136016, 7687000);
insert into capital(country_name, capital_name, inhabitants, area) values ('Australia', 'Canberra', 431826, 814);

select * from country where country_name = 'Australia';

select * from capital where country_name = 'Australia';

/* many-to-one */
create table fitness_club(
    id serial primary key,
    name varchar(255)
);

create table sportsman(
    id serial primary key,
    name varchar(255),
    fitness_club_id int references fitness_club(id) unique
);

insert into fitness_club(name) values ('Fitnesshouse');
insert into sportsman(name, fitness_club_id) values ('Ivan', 1);

select * from sportsman;

select * from fitness_club where id in (select fitness_club_id from sportsman);