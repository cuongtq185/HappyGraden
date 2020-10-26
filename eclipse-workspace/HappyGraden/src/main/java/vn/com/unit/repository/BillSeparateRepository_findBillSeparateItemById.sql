SELECT 
	bill_s.id as id,
	shop.id as shop_id,
	bill.id as bill_id,
	shop.name as shop_name,
	shop.email as shop_email,
	shop.phone as shop_phone,
	bill.account as account,
	bill.address as shipping_add,
	bill.create_at as create_at,
	bill_s.status as status,
	bill_s.refund  as refund,
	bill.payment as payment,
	bill.account as account_id,
	(SELECT sum(item.quantity*product.price)
  		FROM [DMS_DEV].[dbo].[p2p_bill_item] item left join p2p_product product on item.product=product.id
  		where item.id=bill_s.id) as total_price
			  FROM p2p_bill_separate bill_s 
			  left join p2p_shop shop on bill_s.shop= shop.id
			  left join p2p_bill bill on bill.id=bill_s.bill
  where bill_s.id=/*id*/
  