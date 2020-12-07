SELECT count(*) FROM
(
SELECT
     DISTINCT acc.*

FROM
	(	
	account acc
  	LEFT JOIN role role ON acc.role = role.role_id	
	)
	WHERE
	acc.account_disable = 0 
	/*IF role_id != null*/
    AND role.role_id = /*role_id*/''
	/*END*/
	/*BEGIN*/
	AND
	(
		/*IF keyword != null && keyword != ''*/
		OR replace(UPPER(acc.account_id),' ','') LIKE ( '%' + UPPER(/*keyword*/) + '%' )
		OR replace(UPPER(acc.account_name),' ','') LIKE ( '%' + UPPER(/*keyword*/) + '%' )
		OR replace(UPPER(acc.account_username),' ','') LIKE ( '%' + UPPER(/*keyword*/) + '%' )
	    OR replace(UPPER(acc.account_email),' ','') LIKE ( '%' + UPPER(/*keyword*/) + '%' )
	    OR replace(UPPER(acc.account_phone),' ','') LIKE ( '%' + UPPER(/*keyword*/) + '%' )
	    /*END*/
    )
    /*END*/
	
) Accont_t

