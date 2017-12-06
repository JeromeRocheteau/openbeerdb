create table `brands` (
  `id` int(11) not null auto_increment,
  `brewery` int(11) not null,
  `user` varchar(45) not null,
  `name` varchar(45) not null,
  primary key (`id`),
  foreign key (`brewery`) references `breweries` (`id`)
);
