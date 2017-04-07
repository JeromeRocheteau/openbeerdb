select 
  b.id as beerId, 
  b.name as beerName, 
  b.abv as abv, 
  br.id as breweryID, 
  br.name as breweryName, 
  br.address as address, 
  br.city as city, 
  br.country as country 
from beers b 
inner join breweries br on br.id = b.brewery
limit ?,?