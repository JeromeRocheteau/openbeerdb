select 
  br.name as label,
  count(b.id) as count,
  round(avg(b.abv), 2) as average
from beers b 
inner join breweries br on br.id = b.brewery
where b.abv > 0 and br.country = ?
group by br.id
order by average desc, count desc, label asc;