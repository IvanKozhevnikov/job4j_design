create table shop_emploee_doc (id serial primary key,
emploee varchar(255),
salary integer,
resume text,
snils boolean);
insert into shop_emploee_doc values (1, 'cashier', 30000,
'the composition of the resume...', TRUE);
update shop_emploee_doc set emploee = 'administrator',
salary = 70000,
resume = 'the composition of the resume...',
snils = TRUE;
delete from shop_emploee_doc
where emploee = 'administrator';