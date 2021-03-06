create table `beers` (
  `id` int(11) not null auto_increment,
  `brewery` int(11) not null,
  `brand` int(11) default null,
  `abv` float not null,
  `user` varchar(45) not null,
  `name` varchar(45) not null,
  primary key (`id`),
  foreign key (`brewery`) references `breweries` (`id`),
  foreign key (`brand`) references `brands` (`id`)
);
