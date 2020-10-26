SELECT count(*) FROM
(
SELECT
     DISTINCT acc.*

FROM
	(	
		(	p2p_account acc 
		 	LEFT JOIN
		    p2p_account_role accr
			ON
		    acc.id = accr.account
		)
  	LEFT JOIN p2p_role role ON accr.role = role.id	
	)
	WHERE
	acc.disable = 0
	/*IF role_id != null*/
    AND role.id = /*role_id*/
	/*END*/
	/*BEGIN*/
	AND
	(
		/*IF keyword != null && keyword != ''*/
		OR replace(UPPER(acc.id),' ','') LIKE ( '%' + UPPER(/*keyword*/) + '%' )
		OR replace(UPPER(acc.name),' ','') LIKE ( '%' + UPPER(/*keyword*/) + '%' )
		OR replace(UPPER(acc.username),' ','') LIKE ( '%' + UPPER(/*keyword*/) + '%' )
	    OR replace(UPPER(acc.email),' ','') LIKE ( '%' + UPPER(/*keyword*/) + '%' )
	    OR replace(UPPER(acc.phone),' ','') LIKE ( '%' + UPPER(/*keyword*/) + '%' )
	    /*END*/
    )
    /*END*/
	
) Accont_t

