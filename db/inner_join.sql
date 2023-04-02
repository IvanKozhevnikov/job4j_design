create table drivers_license(
    id serial primary key,
    seria int,
    number int,
	category char
);

create table driver(
    id serial primary key,
    name varchar(255),
	patronymic varchar(255),
    drivers_license_id int references drivers_license(id) unique
);

insert into drivers_license(seria, number, category) values (5485, 472356, 'B');
insert into drivers_license(seria, number, category) values (8542, 785424, 'B');
insert into drivers_license(seria, number, category) values (2254, 745665, 'B');
insert into drivers_license(seria, number, category) values (8673, 385447, 'B');
insert into drivers_license(seria, number, category) values (7867, 753654, 'B');

insert into driver(name, patronymic, drivers_license_id) values ('Ivan', 'Vasilievich', 1);
insert into driver(name, patronymic, drivers_license_id) values ('Valentin', 'Petrovich', 2);
insert into driver(name, patronymic, drivers_license_id) values ('Larisa', 'Ivanovna', 3);
insert into driver(name, patronymic, drivers_license_id) values ('Victor', 'Robertovich', 4);
insert into driver(name, patronymic, drivers_license_id) values ('Mikhail', 'Nikolaevich', 5);
insert into driver(name, patronymic) values ('Vasilisa', 'Veniaminovna');
insert into driver(name, patronymic) values ('Mikhail', 'Sergeyevich');

select dr.name, dr.patronymic, dl.seria, dl.number, dl.category 
from driver as dr join drivers_license as dl on dr.drivers_license_id = dl.id;

select dr.name as Имя, dr.patronymic as Фамилия, dl.seria as Серия, dl.number as Номер, dl.category as Категория
from driver as dr join drivers_license as dl on dr.drivers_license_id = dl.id;

select dr.name as "Имя водителя", dr.patronymic as "Фамилия водителя", dl.seria Серия, dl.number Номер, dl.category Категория
from driver dr join drivers_license dl on dr.drivers_license_id = dl.id;
