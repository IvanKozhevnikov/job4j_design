--Создать таблицы
create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    departments_id int references departments(id)
);
--Заполнить их начальными данными
insert into departments(name) values ('Departments 1');
insert into departments(name) values ('Departments 2');
insert into departments(name) values ('Departments 3');
insert into departments(name) values ('Departments 4');

insert into employees(name, departments_id) values ('Employees 1', 1);
insert into employees(name, departments_id) values ('Employees 2', 2);
insert into employees(name, departments_id) values ('Employees 3', 3);
insert into employees(name, departments_id) values ('Employees 4', null);
insert into employees(name, departments_id) values ('Employees 5', null);
insert into employees(name, departments_id) values ('Employees 6', 1);

--Выполнить запросы с left, right, full, cross соединениями
select * from employees e left join departments d on e.departments_id = d.id;
select * from employees e right join departments d on e.departments_id = d.id;
select * from employees e full join departments d on e.departments_id = d.id;
select * from employees e cross join departments d;

--Используя left join найти департаменты, у которых нет работников
select * from departments d left join employees e on e.departments_id = d.id where e.departments_id is null;

--Используя left и right join написать запросы, которые давали бы одинаковый результат
--(порядок вывода колонок в эти запросах также должен быть идентичный).
select * from employees e left join departments d on e.departments_id = d.id;
select * from departments d right join employees e on e.departments_id = d.id;

select * from departments d left join employees e on e.departments_id = d.id;
select * from employees e right join departments d on e.departments_id = d.id;

--Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары
create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(255)
);

insert into teens(name, gender) values ('Vadim', 'Male'), ('Andrey', 'Male'), ('Pavel', 'Male')
                                       , ('Svetlana', 'Female'), ('Kate', 'Female'), ('Nataly', 'Female');
select t1.name, t2.name from teens t1 cross join teens t2 where t1.gender != t2.gender;