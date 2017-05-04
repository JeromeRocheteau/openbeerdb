select 
  b.name as label,
  1 as count,
  round(b.abv, 2) as average
from beers b 
inner join breweries br on br.id = b.brewery
where b.abv > 0 and br.name = ?
order by average desc, label asc;