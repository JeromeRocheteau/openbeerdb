select 
  b.id as id,
  b.user as user 
from beers b 
where b.id = ? and b.user = ?