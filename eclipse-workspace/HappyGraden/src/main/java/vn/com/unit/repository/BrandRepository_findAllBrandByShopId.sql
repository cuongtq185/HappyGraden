select *
from p2p_brand
where id
in
	(
	select brand
	from p2p_product
	where shop = /*shop_id*/
	)
AND disable = 0