select 
  b.id as beerId, 
  b.name as beerName, 
  b.abv as abv, 
  ba.id as brandId, 
  ba.name as brandName,
  br.id as breweryId, 
  br.name as breweryName, 
  br.address as address, 
  br.city as city, 
  br.country as country 
from beers b 
inner join breweries br on br.id = b.brewery
left join brands ba on ba.id = b.brand
order by b.id asc
limit ?,?