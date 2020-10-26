use DMS_DEV;

drop table p2p_log;
drop table p2p_bill_item;
drop table p2p_bill_separate;
drop table p2p_bill;
drop table p2p_cart;
drop table p2p_product_tag;
drop table p2p_product;
drop table p2p_brand;
drop table p2p_tag;
drop table p2p_category;
drop table p2p_shop;
drop table p2p_account_role;
drop table p2p_role;
drop table p2p_account;

create table p2p_account (
    id bigint primary key identity(1,1),
    name nvarchar(50),
    username nvarchar(50) not null unique,
    password nvarchar(255) not null,
    email nvarchar(50),
    phone nvarchar(20),
    disable bit default 0,
    create_at datetime default getutcdate()
)

create table p2p_role (
    id smallint primary key identity(1,1),
    name nvarchar(50) not null unique
)

create table p2p_account_role (
    account bigint,
    role smallint,

    constraint pk_p2p_account_role primary key (account, role),
    constraint fk_p2p_account_role_account__account_id foreign key (account) references p2p_account(id),
    constraint fk_p2p_account_role_role__role_id foreign key (role) references  p2p_role(id)
)

create table p2p_shop (
    id bigint primary key,
    constraint fk_p2p_shop_id foreign key (id) references p2p_account(id),
    name nvarchar(50) not null unique,
    address nvarchar(255),
    email nvarchar(50),
    phone nvarchar(20),
    detail nvarchar(500),
    
    -- 0 : waiting accept
    -- 1 : active
    -- 2 : delete by vendor (owner)
    -- 3 : delete by admin
    status tinyint default 0,
    
    create_at datetime default getutcdate()
)

create table p2p_category (
    id int primary key identity(1,1),
    name nvarchar(50) not null unique,
    img nvarchar(500),
    disable bit default 0
)

create table p2p_tag (
    id bigint primary key identity(1,1),
    name nvarchar(10) not null unique,
)

create table p2p_brand (
    id int primary key identity(1,1),
    name nvarchar(50) not null unique,
    disable bit default 0
)

create table p2p_product (
    id bigint primary key identity(1,1),

    shop bigint not null,
    constraint fk_p2p_product_shop__shop_id foreign key (shop) references p2p_shop(id),

    name nvarchar(255) not null,
    price int not null check (price > 0),
    detail nvarchar(1000),
    img nvarchar(500) not null,
    disable bit default 0,

    category int,
    constraint fk_p2p_product_category__category_id foreign key (category) references p2p_category(id),

    brand int,
    constraint fk_p2p_product_brand__brand_id foreign key (brand) references p2p_brand(id),
    
    quantity int not null check (quantity >= 0),

    create_at datetime default getutcdate()
)

create table p2p_product_tag (
    product bigint,
    tag bigint,

    constraint pk_p2p_product_tag primary key (product, tag),
    constraint fk_p2p_product_tag_product__product_id foreign key (product) references p2p_product(id),
    constraint fk_p2p_product_tag_tag__tag_id foreign key (tag) references p2p_tag(id)
)

create table p2p_cart (
    account bigint not null,

    product bigint not null,
    
    quantity int not null check (quantity > 0),

    create_at datetime default getutcdate(),

    constraint pk_p2p_p2p_cart primary key (account, product),
    constraint fk_p2p_cart_account__account_id foreign key (account) references p2p_account(id),
    constraint fk_p2p_cart_product__product_id foreign key (product) references p2p_product(id)
)

create table p2p_bill (
    id bigint primary key identity(1,1),
        
    account bigint not null,
    constraint fk_p2p_bill_account__account_id foreign key (account) references p2p_account(id),
    
    address nvarchar(4000) not null,
    
	/*
    0	: wating payment
    > 0	: money payment success
    < 0 : money payment error
	*/
    payment int default 0,

    create_at datetime default getutcdate()
)

create table p2p_bill_separate (
	id bigint primary key identity(1,1),

    bill bigint,
    constraint fk_p2p_bill_separate_bill__bill_id foreign key (bill) references p2p_bill(id),

	shop bigint,
	constraint fk_p2p_bill_separate_shop__shop_id foreign key (shop) references p2p_shop(id),

	-- 0 : waiting
    -- 1 : approve
    -- 2 : deny
    -- 3 : complete	(approve success, shipping success)
    -- 4 : cancel	(approve success, can't receive product/error) 
    status tinyint default 0,
    
	/*
    0	: not refund
    > 0 : money refund success
	< 0 : money refund error
	*/
    refund int default 0,
);

create table p2p_bill_item (
    id bigint,
    constraint fk_p2p_bill_item_id__bill_id foreign key (id) references p2p_bill_separate(id),

    product bigint not null,
    constraint fk_p2p_bill_item_product__product_id foreign key (product) references p2p_product(id),

    quantity int not null check (quantity > 0),
    
    constraint pk_p2p_p2p_bill_item primary key (id, product)
)

create table p2p_log (
	id bigint primary key identity(1,1),
	log nvarchar(3000),
	type nvarchar(50),
	author nvarchar(500),
	create_at datetime default getutcdate(),
)

--insert into p2p_account(username, password) values ('admin', '');
--insert into p2p_role (name) values ('ROLE_ADMIN');
--insert into p2p_role (name) values ('ROLE_VENDOR');
--insert into p2p_role (name) values ('ROLE_USER');
--insert into p2p_account_role (account, role) values (1, 1);

-- init data

-- acount

insert into p2p_account(username, password) values ('admin', '');
insert into p2p_account(username, password) values ('vendor', '');
insert into p2p_account(username, password) values ('user', '');

insert into p2p_role (name) values ('ROLE_ADMIN');
insert into p2p_role (name) values ('ROLE_VENDOR');
insert into p2p_role (name) values ('ROLE_USER');

insert into p2p_account_role (account, role) values (1, 1);
insert into p2p_account_role (account, role) values (2, 2);
insert into p2p_account_role (account, role) values (3, 3);

-- product

insert into p2p_account(username, password) values ('vendor-1', '');
insert into p2p_account(username, password) values ('vendor-2', '');
insert into p2p_account(username, password) values ('vendor-3', '');
insert into p2p_account(username, password) values ('vendor-4', '');
insert into p2p_account(username, password) values ('vendor-5', '');
insert into p2p_account(username, password) values ('vendor-6', '');

insert into p2p_account_role (account, role) values (4, 2);
insert into p2p_account_role (account, role) values (5, 2);

insert into p2p_shop (id, name, status) values (4, 'Mobile Shop', 1);
insert into p2p_shop (id, name, status) values (5, 'shop-2', 1);
insert into p2p_shop (id, name, status) values (6, 'shop-3', 1);
insert into p2p_shop (id, name, status) values (7, 'shop-4', 1);
insert into p2p_shop (id, name, status) values (8, 'shop-5', 1);
insert into p2p_shop (id, name, status) values (9, 'shop-6', 1);

insert into p2p_category (name, img) values ('Smartphone', '/static/default.png');
insert into p2p_category (name, img) values ('Laptop', '/static/default.png');
insert into p2p_category (name, img) values ('TV', '/static/default.png');
insert into p2p_category (name, img) values ('Smartwatch', '/static/default.png');
insert into p2p_category (name, img) values ('Toy', '/static/default.png');
insert into p2p_category (name, img) values ('Car', '/static/default.png');
insert into p2p_category (name, img) values ('Moto', '/static/default.png');
insert into p2p_category (name, img) values ('Headphone', '/static/default.png');

insert into p2p_brand (name) values ('Dell');
insert into p2p_brand (name) values ('Asus');
insert into p2p_brand (name) values ('Apple');
insert into p2p_brand (name) values ('Samsung');
insert into p2p_brand (name) values ('Sony');
insert into p2p_brand (name) values ('Xiaomi');

insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (4, 'Iphone 7', 1150, 'https://cdn.tgdd.vn/Products/Images/42/87839/iphone-7-plus-128gb-hh-400x400.jpg', 1, 6, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (4, 'Iphone 8', 1700, 'https://cdn.tgdd.vn/Products/Images/42/225734/iphone-8-plus-128gb-082720-052716-400x400.jpg', 1, 6, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (4, 'Iphone X', 11200, 'https://cdn.tgdd.vn/Products/Images/42/114115/iphone-x-64gb-2-400x460.png', 1, 6, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (4, 'Samsung S7', 1650, 'https://cdn.tgdd.vn/Products/Images/42/74113/samsung-galaxy-s7-2-400x460.png', 1, 6, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (4, 'Samsung S8', 1800, 'https://cdn.tgdd.vn/Products/Images/42/78479/samsung-galaxy-s8-4-400x460-400x460.png', 1, 6, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (4, 'Samsung S9', 1950, 'https://cdn.tgdd.vn/Products/Images/42/113263/samsung-galaxy-s9-black-400x460-400x460.png', 1, 6, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (4, 'Samsung S10', 11200, 'https://cdn.tgdd.vn/Products/Images/42/161554/sieu-pham-galaxy-s-black-400x460.png', 1, 6, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (4, 'Samsung Note 7', 1700, 'https://cdn.tgdd.vn/Products/Images/42/80213/samsung-galaxy-note-7-2-400x460.png', 6, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (4, 'Samsung Note 8', 1850, 'https://cdn.tgdd.vn/Products/Images/42/106211/samsung-galaxy-note8-gold-1-400x460.png', 6, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (4, 'Samsung Note 9', 11200, 'https://cdn.tgdd.vn/Products/Images/42/154897/samsung-galaxy-note-9-black-400x460-400x460.png', 6, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (4, 'Samsung Note 10', 11400, 'https://cdn.tgdd.vn/Products/Images/42/191276/samsung-galaxy-note-10-silver-400x460.png', 6, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (4, 'Xiaomi Redmi 8', 1150, '/static/product/default.png', 1, 6, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (4, 'Xiaomi Redmi Note 8', 1175, '/static/product/default.png', 6, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (4, 'Xiaomi Redmi 9', 1180, '/static/product/default.png', 1, 6, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (4, 'Xiaomi Redmi Note 9', 1200, '/static/default.png', 1, 6, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (4, 'product-1', 1000, '/static/default.png', 1, 6, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (4, 'product-2', 1500, '/static/default.png', 1, 6, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (4, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (4, 'product-2', 1500, '/static/default.png', 1, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (4, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');

insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (5, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (5, 'product-2', 1500, '/static/default.png', 1, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (5, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (5, 'product-2', 1500, '/static/default.png', 1, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (5, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (5, 'product-2', 1500, '/static/default.png', 1, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (5, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (5, 'product-2', 1500, '/static/default.png', 1, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (5, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (5, 'product-2', 1500, '/static/default.png', 1, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (5, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (5, 'product-2', 1500, '/static/default.png', 1, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (5, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (5, 'product-2', 1500, '/static/default.png', 1, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (5, 'product-2', 1500, '/static/default.png', 1, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (5, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (5, 'product-2', 1500, '/static/default.png', 1, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (5, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (5, 'product-2', 1500, '/static/default.png', 1, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (5, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');

insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (6, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (6, 'product-2', 1500, '/static/default.png', 1, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (6, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (6, 'product-2', 1500, '/static/default.png', 1, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (6, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (6, 'product-2', 1500, '/static/default.png', 1, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (6, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (6, 'product-2', 1500, '/static/default.png', 1, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (6, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (6, 'product-2', 1500, '/static/default.png', 1, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (6, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (6, 'product-2', 1500, '/static/default.png', 1, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (6, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (6, 'product-2', 1500, '/static/default.png', 1, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (6, 'product-2', 1500, '/static/default.png', 1, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (6, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (6, 'product-2', 1500, '/static/default.png', 1, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (6, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (6, 'product-2', 1500, '/static/default.png', 1, 2, 30, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');
insert into p2p_product (shop, name, price, img, category, brand, quantity, detail) values (6, 'product-1', 1000, '/static/default.png', 1, 1, 40, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas fermentum. laoreet turpis, nec sollicitudin dolor cursus at. Maecenas aliquet, dolor a faucibus efficitur, nisi tellus cursus urna, eget dictum lacus turpis.');

/*
select product.id, product.name, product.price, product.detail, product.img,
		product.disable, product.quantity, product.create_at,
		category.name as category, brand.name as brand, shop.name as shop
from p2p_product product
left join p2p_shop shop
on shop.id = product.shop
left join p2p_category category
on category.id = product.category
left join p2p_brand brand
on brand.id	= product.brand;

-- insert into p2p_log (log, type, author) values ('log', 'test', 'Loc');

insert into p2p_account(username, password) values ('user-1', '');
insert into p2p_account(username, password) values ('user-2', '');
insert into p2p_account(username, password) values ('user-3', '');
insert into p2p_account(username, password) values ('user-4', '');
insert into p2p_account(username, password) values ('user-5', '');
insert into p2p_account(username, password) values ('user-6', '');
insert into p2p_account(username, password) values ('user-7', '');
insert into p2p_account(username, password) values ('user-8', '');
insert into p2p_account(username, password) values ('user-9', '');

insert into p2p_account_role (account, role) values (10, 3);
insert into p2p_account_role (account, role) values (11, 3);
insert into p2p_account_role (account, role) values (12, 3);
insert into p2p_account_role (account, role) values (13, 3);
insert into p2p_account_role (account, role) values (14, 3);
insert into p2p_account_role (account, role) values (15, 3);
insert into p2p_account_role (account, role) values (16, 3);
insert into p2p_account_role (account, role) values (17, 3);
insert into p2p_account_role (account, role) values (18, 3);

-- init product in cart
insert into p2p_cart (account, product, quantity) values (3, 1, 1); -- 1000 * 1
insert into p2p_cart (account, product, quantity) values (3, 2, 3); -- 1500 * 3

--
-- init product in cart
insert into p2p_cart (account, product, quantity) values (6, 1, 2); -- 1000 * 1
insert into p2p_cart (account, product, quantity) values (6, 2, 2); -- 1500 * 3
*/
/*
insert into p2p_bill (account, address) values (3, 'HCM');
-- select scope_identity();
insert into p2p_bill_item (id, product, quantity) values (1, 1, 1);
insert into p2p_bill_item (id, product, quantity) values (1, 2, 3);
delete from p2p_cart where account = 3;

select bill_item.*, product.price from
	(
	select *
	from p2p_bill_item
	where id = 1
	) bill_item
left join p2p_product product
on product.id = bill_item.product;

select sum(bill.price)
from (
	select bill_item.id, (bill_item.quantity * product.price) as price
	from
		(
		select *
		from p2p_bill_item
		where id = 1
		) bill_item
	left join p2p_product product
	on product.id = bill_item.product) bill
group by bill.id;

--select *
--from
--	(select p2p_bill_item.product, p2p_bill_item.quantity from p2p_bill_item where id = 1) cart_item
--join ( select * from  (values (1)) as temp_table(id_bill) )

select 1 as bill_id, p2p_bill_item.product, p2p_bill_item.quantity from p2p_bill_item where id = 1

-- 1 as bill_id
insert into p2p_bill_item (id, product, quantity) (select 1 as id, p2p_cart.product, p2p_cart.quantity from p2p_cart where p2p_cart.account = 1)
*/

/*
select *
from p2p_product product

left join p2p_category category
on category.id = product.category

left join p2p_brand brand
on brand.id = product.brand
*/
