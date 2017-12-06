create table `features` (
  `beer` int(11) not null,
  `style` int(11) not null,
  `user` varchar(45) not null,
  primary key (`beer`,`style`),
  foreign key(`beer`) references `beers`(`id`),
  foreign key(`style`) references `styles`(`id`)
);