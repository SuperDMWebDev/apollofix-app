drop database if exists apollogix_db;
create database if not exists apollogix_db;

use apollogix_db;

create table colors(
                       id int auto_increment,
                       name varchar(255) not null,

                       primary key(id)
);

create table categories(
                           id int auto_increment,
                           name varchar(255) not null,

                           primary key(id)
);

create table `orders` (
                          id int auto_increment,

                          primary key(id)

);

create table products(
                         id int auto_increment,
                         name varchar(255) not null,
                         description text,
                         image varchar(255),
                         price decimal(10,2),
                         order_id int,

                         primary key(id),
                         foreign key(order_id) references orders(id)
);

create table product_category(
                                 product_id int not null,
                                 category_id int not null,

                                 primary key (product_id, category_id),
                                 foreign key (product_id) references products(id),
                                 foreign key (category_id) references categories(id)
);

create table product_color(
                              product_id int not null,
                              color_id int not null,

                              primary key (product_id, color_id),
                              foreign key ( product_id) references products(id),
                              foreign key (color_id) references colors(id)

);

-- create sample db
DELIMITER //
create PROCEDURE GenerateData()
BEGIN
	declare i int default 1;
    while i<=20 do
		insert into products(name, description , image, price)
        values(concat('productName ',i), concat('description ',i), concat('image ',i), 100.00);

insert into colors(name)
values(concat('colorName ',i));

insert into categories(name)
values(concat('categoryName ',i));

insert into product_color(product_id, color_id)
values(i,i);

insert into product_category(product_id, category_id)
values(i,i);

set i = i +1;
end while;

END;
// 
DELIMITER ;

call GenerateData();


-- select db
use apollogix_db;
select * from products;