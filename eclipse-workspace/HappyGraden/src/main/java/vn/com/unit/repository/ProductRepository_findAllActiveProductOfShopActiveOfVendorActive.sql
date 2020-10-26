select product.*
from p2p_product product
left join p2p_shop shop
on shop.id = product.shop
left join p2p_account account
on account.id = product.shop
where product.disable = 0
	and shop.status = 1
	and account.disable = 0
order by newid();