select 
  b.id as id,
  b.user as user 
from breweries b 
where b.id = ? and b.user = ?