insert into p2p_bill (account, address) values (/*account_id*/, /*address*/);

declare @bill_id int = (select scope_identity());

select count(distinct product.shop)
from p2p_cart cart
left join p2p_product product
on product.id = cart.product

--
declare @i int = 
	(
	select count(distinct product.shop)
	from p2p_cart cart
	left join p2p_product product
	on product.id = cart.product
	);

print @i

WHILE @i > 0
BEGIN
   PRINT 'Inside FOR LOOP';
   SET @i = @i - 1;
END;

--
insert into p2p_bill (account, address) values (1, '1');

declare @bill_id int = (select scope_identity());

create table p2p_bill_separate (
    id bigint,
    constraint fk_bill_separate_id__bill_id foreign key (id) references p2p_bill(id),

	shop bigint,
	constraint fk_bill_separate_shop__shop_id foreign key (shop) references p2p_shop(id),
);

--
drop table p2p_bill_separate;

insert into p2p_bill (account, address) values (1, '1');

declare @bill_id int = (select scope_identity());

create table p2p_bill_separate (
    id bigint,
    constraint fk_bill_separate_id__bill_id foreign key (id) references p2p_bill(id),

	shop bigint,
	constraint fk_bill_separate_shop__shop_id foreign key (shop) references p2p_shop(id),
);

--
drop table p2p_bill_separate;

insert into p2p_bill (account, address) values (1, '1');

declare @bill_id int = (select scope_identity());

create table p2p_bill_separate (
	id bigint primary key identity(1,1),

    bill bigint,
    constraint fk_bill_separate_bill__bill_id foreign key (bill) references p2p_bill(id),

	shop bigint,
	constraint fk_bill_separate_shop__shop_id foreign key (shop) references p2p_shop(id),
);

insert into p2p_bill_separate (bill, shop)
	select @bill_id as bill, temp.*
	from 
		(
		select distinct product.shop
		from p2p_cart cart
		left join p2p_product product
		on product.id = cart.product
		) temp

--
drop table p2p_bill_separate;

insert into p2p_bill (account, address) values (1, '1');

declare @bill_id int = (select scope_identity());

create table p2p_bill_separate (
	id bigint primary key identity(1,1),

    bill bigint,
    constraint fk_bill_separate_bill__bill_id foreign key (bill) references p2p_bill(id),

	shop bigint,
	constraint fk_bill_separate_shop__shop_id foreign key (shop) references p2p_shop(id),
);

insert into p2p_bill_separate (bill, shop)
	select @bill_id as bill, temp.*
	from 
		(
		select distinct product.shop
		from p2p_cart cart
		left join p2p_product product
		on product.id = cart.product
		) temp
--

		drop table p2p_bill_separate;

insert into p2p_bill (account, address) values (1, '1');

declare @bill_id int = (select scope_identity());

create table p2p_bill_separate (
	id bigint primary key identity(1,1),

    bill bigint,
    constraint fk_bill_separate_bill__bill_id foreign key (bill) references p2p_bill(id),

	shop bigint,
	constraint fk_bill_separate_shop__shop_id foreign key (shop) references p2p_shop(id),
);

insert into p2p_bill_separate (bill, shop)
	select @bill_id as bill, temp.*
	from 
		(
		select distinct product.shop
		from p2p_cart cart
		left join p2p_product product
		on product.id = cart.product
		) temp


---
create table p2p_bill (
    id bigint primary key identity(1,1),
        
    account bigint not null,
    address nvarchar(255) not null,
    create_at datetime default getutcdate(),

	/*
    0	: wating payment
    > 0	: money payment success
    < 0 : money payment error
	*/
    payment int default 0,

    constraint fk_bill_account__account_id foreign key (account) references p2p_account(id),
)

create table p2p_bill_separate (
	id bigint primary key identity(1,1),

    bill bigint,
    constraint fk_bill_separate_bill__bill_id foreign key (bill) references p2p_bill(id),

	shop bigint,
	constraint fk_bill_separate_shop__shop_id foreign key (shop) references p2p_shop(id),

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

--

-- @bill_id : param
insert into p2p_bill (account, address) values (1, '1');
declare @bill_id int = (select scope_identity());

-- @curent_accoun : param
declare @curent_account int = 1;

declare @i int = 
	(
	select count(distinct product.shop)
	from
		(
		select product
		from p2p_cart
		where p2p_cart.account = @curent_account
		) cart
	left join p2p_product product
	on product.id = cart.product
	);

declare @shop int;

WHILE @i > 0
BEGIN
   
	set @shop = (select distinct top 1 product.shop
	from p2p_cart cart
	left join p2p_product product
	on product.id = cart.product);

	print @shop;
	delete from 

    SET @i = @i - 1;
END;

---


-- @bill_id : param
insert into p2p_bill (account, address) values (1, '1');
declare @bill_id int = (select scope_identity());

-- @curent_accoun : param
declare @curent_account int = 1;

-- create bill separate
insert into p2p_bill_separate (bill, shop)
	select @bill_id as bill, temp.*
	from 
		(
		select distinct product.shop
		from p2p_cart cart
		left join p2p_product product
		on product.id = cart.product
		) temp;

declare @i int = 
	(
	select count(distinct product.shop)
	from
		(
		select product
		from p2p_cart
		where p2p_cart.account = @curent_account
		) cart
	left join p2p_product product
	on product.id = cart.product
	);

declare @shop int;
declare @bill_separate int;

WHILE @i > 0
BEGIN
   
	set @shop = (select distinct top 1 product.shop
	from
		(
		select product
		from p2p_cart
		where p2p_cart.account = @curent_account
		) cart
	left join p2p_product product
	on product.id = cart.product);

	print '@shop';
	print @shop;

	set @bill_separate =
		(
			select id
			from p2p_bill_separate
			where bill = @bill_id and shop = @shop
		);

	print '@bill_separate';
	print @bill_separate;

	insert into p2p_bill_item (id, product, quantity)
		(
		select @bill_separate as id, p2p_cart.product, p2p_cart.quantity
		from p2p_cart
		where p2p_cart.account = @curent_account and product in (select id from p2p_product where shop = @shop)
		);

	delete from p2p_cart
	where account = @curent_account
		and product
			in
			(
				select product from p2p_bill_item where p2p_bill_item.id = @bill_separate
			);

    SET @i = @i - 1;

END;

--


-- @bill_id : param
insert into p2p_bill (account, address) values (1, '1');
declare @bill_id int = (select scope_identity());

-- @curent_accoun : param
declare @curent_account int = 1;

-- create bill separate
insert into p2p_bill_separate (bill, shop)
	select @bill_id as bill, temp.*
	from 
		(
		select distinct product.shop
		from p2p_cart cart
		left join p2p_product product
		on product.id = cart.product
		) temp;

declare @i int = 
	(
	select count(distinct product.shop)
	from
		(
		select product
		from p2p_cart
		where p2p_cart.account = @curent_account
		) cart
	left join p2p_product product
	on product.id = cart.product
	);

declare @shop int;
declare @bill_separate int;

WHILE @i > 0
BEGIN
   
	set @shop = (select distinct top 1 product.shop
	from
		(
		select product
		from p2p_cart
		where p2p_cart.account = @curent_account
		) cart
	left join p2p_product product
	on product.id = cart.product);

	print '@shop';
	print @shop;

	set @bill_separate =
		(
			select id
			from p2p_bill_separate
			where bill = @bill_id and shop = @shop
		);

	print '@bill_separate';
	print @bill_separate;

	insert into p2p_bill_item (id, product, quantity)
		(
		select @bill_separate as id, p2p_cart.product, p2p_cart.quantity
		from p2p_cart
		where p2p_cart.account = @curent_account and product in (select id from p2p_product where shop = @shop)
		);

	delete from p2p_cart
	where account = @curent_account
		and product
			in
			(
				select product from p2p_bill_item where p2p_bill_item.id = @bill_separate
			);

    SET @i = @i - 1;

END;
