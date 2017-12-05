create table `breweries` (
  `id` int(11) not null auto_increment,
  `user` varchar(45) not null,
  `name` varchar(45) not null,
  `address` varchar(90) default null,
  `city` varchar(45) not null,
  `country` varchar(45) not null,
  primary key (`id`)
);