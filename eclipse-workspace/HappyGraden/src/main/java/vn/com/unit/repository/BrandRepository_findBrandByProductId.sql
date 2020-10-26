select *
from p2p_brand
where id
in (select brand
	from p2p_product
	where id = /*product_id*/)