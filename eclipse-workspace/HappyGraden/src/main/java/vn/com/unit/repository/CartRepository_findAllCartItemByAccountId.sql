select cart.*, product.img, product.name, product.price
from
	(
	select *
	from p2p_cart
	where account = /*account_id*/
	) cart
left join p2p_product product
on product.id = cart.product