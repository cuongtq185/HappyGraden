select *
from p2p_product
where category = /*category_id*/ AND disable = 'false'
order by id desc
OFFSET  /*offset*/ ROWS FETCH NEXT  /*sizeOfPage*/ ROWS ONLY