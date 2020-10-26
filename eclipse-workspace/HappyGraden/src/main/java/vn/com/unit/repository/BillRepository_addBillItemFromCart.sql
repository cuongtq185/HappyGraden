declare @bill_id int = /*bill_id*/

declare @current_account int = /*account_id*/

-- create bill separate
-- bill_id + shop
insert into p2p_bill_separate (bill, shop)
	select @bill_id as bill, temp.*
	from 
		(
		-- select distinct shop of product from cart of current account
		select distinct product.shop
		from
			(
				-- select product from cart of current account
				select product
				from p2p_cart
				where p2p_cart.account = @current_account
			) cart
		left join p2p_product product
		on product.id = cart.product
		) temp;

-- number of shop in cart of current account
declare @i int = (select count(*) from p2p_bill_separate where bill = @bill_id);

declare @shop int;
declare @bill_separate int;

WHILE @i > 0
BEGIN
   
	set @shop = (select distinct top 1 product.shop
	from
		(
		select product
		from p2p_cart
		where p2p_cart.account = @current_account
		) cart
	left join p2p_product product
	on product.id = cart.product);

--	print '@shop';
--	print @shop;

	set @bill_separate =
		(
			select id
			from p2p_bill_separate
			where bill = @bill_id and shop = @shop
		);

--	print '@bill_separate';
--	print @bill_separate;

	insert into p2p_bill_item (id, product, quantity)
		(
		select @bill_separate as id, p2p_cart.product, p2p_cart.quantity
		from p2p_cart
		where p2p_cart.account = @current_account and product in (select id from p2p_product where shop = @shop)
		);

	delete from p2p_cart
	where account = @current_account
		and product
			in
			(
				select product from p2p_bill_item where p2p_bill_item.id = @bill_separate
			);

    SET @i = @i - 1;

END;

