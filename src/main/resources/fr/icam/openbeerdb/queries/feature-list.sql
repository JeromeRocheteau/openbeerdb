select
  s.id as id,
  s.user as user,
  s.name as name,
  p.id as styleId,
  p.user as styleUser,
  p.name as styleName
from features f
inner join beers b on b.id = f.beer
inner join styles s on s.id = f.style
inner join styles p on s.category = p.id
where b.id = ?