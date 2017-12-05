select 
  b.id as id, 
  b.user as user, 
  b.name as name, 
  b.address as address, 
  b.city as city, 
  b.country as country  
from breweries as b
order by b.id asc