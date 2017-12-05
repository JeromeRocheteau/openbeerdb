select 
  child.id as id, 
  child.user as user,
  child.name as name, 
  parent.id as styleId,
  parent.user as styleUser,
  parent.name as styleName 
from styles child
left join styles parent on parent.id = child.category
order by child.id asc
limit ?,?