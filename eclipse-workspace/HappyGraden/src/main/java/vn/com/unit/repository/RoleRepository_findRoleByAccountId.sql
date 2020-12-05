select *
from role
where role_id
in (select role
	from account
	where account_id = /*account_id*/)