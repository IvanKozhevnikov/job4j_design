CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

INSERT INTO customers VALUES (1, 'Иван', 'Иванов', 45, 'Россия'),
                         (2, 'Максим', 'Кузнецов', 27, 'Россия'),
                         (3, 'Виктор', 'Степанов', 27, 'Россия'),
                         (4, 'Евгений', 'Цветков', 29, 'Россия'),
                         (5, 'Алексей', 'Вавилов', 65, 'Россия');
                         (6, 'Александр', 'Петров', 65, 'Россия');

INSERT INTO orders VALUES (1, 500, 2),
                          (2, 800, 4),
                          (3, 700, 4),
                          (4, 700, 3),
                          (5, 700, 5);

--список клиентов, возраст которых является минимальным
SELECT * FROM customers
WHERE age = (SELECT min(age) FROM customers);

--список пользователей, которые еще не выполнили ни одного заказ
SELECT first_name, last_name
FROM customers WHERE customers.id NOT IN (SELECT orders.customer_id FROM orders);