SELECT count(*)
			  FROM p2p_bill_separate bill_s 
			  left join p2p_shop shop on bill_s.shop= shop.id
			  left join p2p_bill bill on bill.id=bill_s.bill
			

  where bill.account = /*account_id*/
  
/*IF status != null*/
    AND bill_s.status = /*status*/
/*END*/

/*IF payment != null && payment < 0 */
    AND bill.payment  < 0
/*END*/
 	
/*IF payment != null && payment == 0 */
    AND bill.payment  = 0
/*END*/
    
/*IF payment != null && payment > 0 */
    AND bill.payment  > 0
/*END*/
    
    

