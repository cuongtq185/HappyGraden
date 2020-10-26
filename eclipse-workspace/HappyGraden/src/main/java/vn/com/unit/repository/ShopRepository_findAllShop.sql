SELECT shop.*, account.username
FROM  p2p_shop shop
left Join p2p_account account
on account.id = shop.id
order by shop.id
OFFSET /*offset*/ ROWS FETCH NEXT /*sizeOfPage*/ ROWS ONLY