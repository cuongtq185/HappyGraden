select *, category.category_name, origin.origin_name, product_img2D.product_img, product_price.product_price
from product pr
left join category 
on pr.category = category.category_id
left join origin 
on pr.origin = origin.origin_id
left join product_img2D
on pr.product_id = product_img2D.id_product_img2D
left join product_price
on pr.product_id = product_price.product_price_id
where product_disable = 0
order by product_id
OFFSET  /*offset*/ ROWS FETCH NEXT  /*sizeOfPage*/ ROWS ONLY