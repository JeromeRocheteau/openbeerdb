select
  s.id as id,
  s.name as name,
  null as styleId,
  null as styleName
from features f
inner join beers b on b.id = f.beer
inner join styles s on s.id = f.style
where b.id = ?