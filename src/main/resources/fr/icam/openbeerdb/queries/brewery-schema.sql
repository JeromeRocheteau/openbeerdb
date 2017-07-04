create table `breweries` (
  `id` int(11) not null auto_increment,
  `name` varchar(45) not null,
  `address` varchar(90) default null,
  `city` varchar(45) not null,
  `country` varchar(45) not null,
  primary key (`id`)
);