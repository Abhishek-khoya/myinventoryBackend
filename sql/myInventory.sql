create database inventoryDB
create user "inventoryUser"@"localhost" identified by 'myinventory'
grant all privileges on inventoryDb.* to 'inventoryUser'@'localhost' with grant option;
--Retrieve all products in a specific category:
SELECT * FROM Product WHERE CategoryID = :categoryId;
-- Update the quantity in stock for a product:
UPDATE Product SET QuantityInStock = :newQuantity WHERE ProductID = :productId;
-- Calculate the total sales for a specific product:
SELECT SUM(TotalAmount) FROM Sale WHERE ProductID = :productId;

create table categories(
category_id int primary key auto_increment,
category_name varchar(255) unique not null
)
create table product(
product_id int primary key auto_increment,
product_name varchar(255) unique not null,
description Text not null,
quantity int not null,
price decimal(10,2) not null,
category_id int not null,
foreign key (category_id) references categories(category_id)
)
create table sale(
sale_id int primary key auto_increment,
product_id int not null,
sale_date date not null,
quantity_sold int not null,
total_ammount decimal(10,2) not null,
foreign key (product_id) references product(product_id)
)
create table Photos(
photo_id int primary key auto_increment,
product_id int not null,
photo mediumblob not null,
foreign key (product_id) references product(product_id)
)