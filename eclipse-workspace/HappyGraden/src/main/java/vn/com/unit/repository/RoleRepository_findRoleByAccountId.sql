select *
from p2p_role
where id
in (select role
	from p2p_account_role
	where account = /*account_id*/)