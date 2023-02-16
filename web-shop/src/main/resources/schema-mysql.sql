drop table if exists products;
create table products (id int not null primary key AUTO_INCREMENT, product_name varchar(30), price decimal, descri varchar(50),stock int,category varchar(20));

drop table if exists orders;
create table orders (id int not null primary key AUTO_INCREMENT, first_name varchar(10), last_name varchar(10), email varchar(30),
uaddress varchar(20), city varchar(20),postal_code varchar(5),paid tinyint(1),stripe varchar(60));

drop table if exists order_items;
create table order_items (id int not null primary key AUTO_INCREMENT, order_id int, product_id int, amount int, price decimal);

drop table if exists categories;
create table categories (id int not null primary key AUTO_INCREMENT, category_name varchar(20));

drop table if exists users;
create table users (id int not null primary key AUTO_INCREMENT, username varchar(20) not null, password varchar(60) not null);


