SELECT *
FROM p2p_brand
where disable = 0
order by id
OFFSET  /*offset*/ ROWS FETCH NEXT  /*sizeOfPage*/ ROWS ONLY