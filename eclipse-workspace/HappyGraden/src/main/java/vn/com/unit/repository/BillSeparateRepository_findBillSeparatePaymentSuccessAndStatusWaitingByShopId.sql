declare @shop int = /*shop_id*/;

select bill_separate.*, bill.payment, bill.create_at
from p2p_bill_separate bill_separate
left join p2p_bill bill
on bill.id = bill_separate.bill
where bill_separate.shop = @shop
	and bill_separate.status = 0
	and bill.payment > 0;