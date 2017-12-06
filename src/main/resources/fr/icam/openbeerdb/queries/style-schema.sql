create table `styles` (
  `id` int(11) not null auto_increment,
  `category` int(11) default null,
  `user` varchar(45) not null,
  `name` varchar(45) not null,
  primary key (`id`),
  foreign key (`category`) references `styles`(`id`)
);