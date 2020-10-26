declare @bill int = /*bill_id*/ ;

select bill_item.*, product.name as productName, product.price
from p2p_bill_item bill_item
left join p2p_bill_separate bill_separate
on bill_separate.id = bill_item.id
left join p2p_product product
on product.id = bill_item.product
where bill_separate.bill = @bill