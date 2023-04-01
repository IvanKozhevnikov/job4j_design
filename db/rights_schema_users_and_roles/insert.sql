insert into role(name) values ('administrator');

insert into users(name, role_id) values ('Ivan', 1);

insert into rules(name) values ('full access');

insert into role_rules(role_id, rules_id) values (1, 1);

insert into category(name) values ('main');

insert into state(name) values ('at work');

insert into item(name, users_id, category_id, state_id) values ('checking', 1, 1, 1);

insert into comments(name, item_id) values ('special equipment', 1);

insert into attachs(name, item_id) values ('a copy of the certificates', 1);