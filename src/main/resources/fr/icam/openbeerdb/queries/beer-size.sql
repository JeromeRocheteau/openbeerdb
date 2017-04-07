select count(b.id) as size 
from beers b
inner join breweries br on b.brewery = br.id