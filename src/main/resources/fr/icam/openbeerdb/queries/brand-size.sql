select count(b.id) as size 
from brands b
inner join breweries br on b.brewery = br.id