drop table if exists products;
create table products (id int not null primary key AUTO_INCREMENT, product_name varchar(30), price decimal, descri varchar(50),stock int);

drop table if exists orders;
create table orders (id int not null primary key AUTO_INCREMENT, first_name varchar(10), last_name varchar(10), email varchar(30),
uaddress varchar(20), city varchar(20),postal_code varchar(5),paid tinyint(1),stripe varchar(60));

drop table if exists order_items;
create table order_items (id int not null primary key AUTO_INCREMENT, order_id int, product_id int, amount int, price decimal);
