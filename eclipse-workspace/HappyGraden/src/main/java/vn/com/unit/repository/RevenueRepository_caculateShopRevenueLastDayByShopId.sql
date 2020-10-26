declare @shop int = /*shop_id*/;

select sum(bill_item.quantity * product.price)
from p2p_bill_separate bill_separate
left join p2p_bill bill
on bill.id = bill_separate.bill
left join p2p_bill_item bill_item
on bill_item.id = bill_separate.id
left join p2p_product product
on bill_item.product = product.id
where bill.create_at >= DATEADD(HOUR, -24, GETDATE()) and bill.payment > 0 and bill_separate.shop = @shop;