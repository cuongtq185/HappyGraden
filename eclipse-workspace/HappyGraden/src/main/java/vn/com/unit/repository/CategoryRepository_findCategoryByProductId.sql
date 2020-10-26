select *
from p2p_category
where id
in (select category
	from p2p_product
	where id = /*product_id*/)