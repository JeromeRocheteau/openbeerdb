select 
  s.id as id,
  s.user as user 
from styles s 
where s.id = ? and s.user = ?