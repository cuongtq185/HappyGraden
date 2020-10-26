select *
from p2p_product
/*IF name != '' and name != null*/
where name like '%' + /*name*/ +'%'
/*END*/