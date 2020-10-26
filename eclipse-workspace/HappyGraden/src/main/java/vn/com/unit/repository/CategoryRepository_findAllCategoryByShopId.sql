select *
from p2p_category
where id
in
	(
	select category
	from p2p_product
	where shop = /*shop_id*/
	)
and disable = 'false'
	