declare @account bigint = /*account_id*/;
declare @bill bigint = /*bill_id*/;

select *
from p2p_bill
where account = @account
	and id = @bill;