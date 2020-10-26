select count(*)
from p2p_product inner join p2p_category on p2p_product.category = p2p_category.id
WHERE
p2p_product.disable = 'false' 

AND p2p_category.disable = 'false' 

AND shop=/*shop_id*/

/*IF category_id != null*/
    AND category = /*category_id*/
/*END*/

/*IF brand_id != null*/
    AND brand = /*brand_id*/
/*END*/
    
    
