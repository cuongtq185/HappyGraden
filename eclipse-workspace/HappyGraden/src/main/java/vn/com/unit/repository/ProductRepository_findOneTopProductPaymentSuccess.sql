select top 1 *
from p2p_product
where id in
    (
    select top 10 bill_item.product
    from p2p_bill bill
    left join p2p_bill_separate bill_separate
    on bill_separate.bill = bill.id
    left join p2p_bill_item bill_item
    on bill_item.id = bill_separate.id
    left join p2p_product product
    on product.id = bill_item.product
    where bill.payment > 0
    group by bill_item.product
    order by sum(bill_item.quantity) desc
    )
order by newid();