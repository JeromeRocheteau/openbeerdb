select 
  b.id as brandId, 
  b.user as brandUser,
  b.name as brandName,
  br.id as breweryId, 
  br.user as breweryUser, 
  br.name as breweryName, 
  br.address as address, 
  br.city as city, 
  br.country as country 
from brands b 
inner join breweries br on br.id = b.brewery
order by b.id asc
limit ?,?