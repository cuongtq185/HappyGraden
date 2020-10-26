select distinct a.*
  from p2p_product a, p2p_category b, p2p_shop c
  where a.category = b.id and b.disable = 0 and a.disable = 0
  
  
  