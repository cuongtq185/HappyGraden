declare @shop int = /*shop_id*/;

select count(*)
from p2p_bill_separate bill_separate
left join p2p_bill bill
on bill.id = bill_separate.bill
where bill.create_at >= DATEADD(HOUR, -24, GETDATE()) and bill_separate.shop = @shop and bill.payment < 0;
