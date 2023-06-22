CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company(name, id) values ('Теремок', 1);
insert into company(name, id) values ('Лента', 2);
insert into company(name, id) values ('Магнит', 3);
insert into company(name, id) values ('Пятёрочка', 4);
insert into company(name, id) values ('Вкусно и точка', 5);

insert into person(id, name, company_id) values (1, 'Игорь', 1);
insert into person(id, name, company_id) values (2, 'Артём', 1);
insert into person(id, name, company_id) values (3, 'Роман', 2);
insert into person(id, name, company_id) values (4, 'Виктор', 2);
insert into person(id, name, company_id) values (5, 'Степан', 2);
insert into person(id, name, company_id) values (6, 'Максим', 2);
insert into person(id, name, company_id) values (7, 'Пётр', 3);
insert into person(id, name, company_id) values (8, 'Сергей', 4);
insert into person(id, name, company_id) values (9, 'Артур', 5);
insert into person(id, name, company_id) values (10, 'Михаил', 5);
insert into person(id, name, company_id) values (11, 'Александр', 5);
insert into person(id, name, company_id) values (12, 'Георгий', 5);

/* 1. В одном запросе получить

- имена всех person, которые не состоят в компании с id = 5;

- название компании для каждого человека. */
select p.name as name_person, c.name as company
from person as p inner join company as c
on c.id = p.company_id where company_id < 5;

/* 2. Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании.
Нужно учесть, что таких компаний может быть несколько. */

select c.name as company, count(p.name) as number_of_people
from company as c
left join person as p
on c.id = p.company_id
group by c.id
having c.id in(
select company_id from person as p
group by company_id
having count(p.name) = (select max(total)
from (select count(p.name) AS total
from person as p group by company_id
having count(distinct (id)) > 1) as results));


