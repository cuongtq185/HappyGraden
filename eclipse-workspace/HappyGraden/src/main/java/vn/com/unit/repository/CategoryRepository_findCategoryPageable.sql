SELECT *
FROM category
order by category_id
OFFSET  /*offset*/ ROWS FETCH NEXT  /*sizeOfPage*/ ROWS ONLY