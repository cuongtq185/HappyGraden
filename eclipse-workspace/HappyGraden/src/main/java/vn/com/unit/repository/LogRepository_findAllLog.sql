SELECT log, type, author, create_at -- AT TIME ZONE 'SE Asia Standard Time'
FROM p2p_log
order by create_at desc
OFFSET  /*offset*/ ROWS FETCH NEXT  /*sizeOfPage*/ ROWS ONLY