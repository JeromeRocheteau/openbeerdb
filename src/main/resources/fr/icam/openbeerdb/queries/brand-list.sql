select 
  b.id as brandId, 
  b.name as brandName,
  br.id as breweryId, 
  br.name as breweryName, 
  br.address as address, 
  br.city as city, 
  br.country as country 
from brands b 
inner join breweries br on br.id = b.brewery
where b.brewery = ?
order by b.id asc