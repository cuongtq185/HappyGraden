select p2p_product.*, p2p_category.name, p2p_brand.name
from p2p_product
left join p2p_category 
on p2p_product.category = p2p_category.id
left join p2p_brand 
on p2p_product.brand = p2p_brand.id
where p2p_product.shop = /*shop_id*/ AND p2p_product.disable = 'false'
order by id
OFFSET  /*offset*/ ROWS FETCH NEXT  /*sizeOfPage*/ ROWS ONLY