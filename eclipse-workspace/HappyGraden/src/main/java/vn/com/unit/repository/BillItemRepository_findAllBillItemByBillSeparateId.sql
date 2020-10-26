SELECT p2p_bill_item.*, p2p_product.name as product_name, p2p_brand.name as brand_name, p2p_category.name as category_name, p2p_product.img, p2p_product.price
FROM p2p_bill_item 
left join p2p_product 
on p2p_bill_item.product = p2p_product.id
left join p2p_category
on p2p_product.category = p2p_category.id
left join p2p_brand
on p2p_product.brand = p2p_brand.id
WHERE p2p_bill_item.id = /*bill_separate_id*/
