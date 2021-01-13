CREATE TABLE `group_set` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `groupName` varchar(255) NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `limit_set` (
  `type` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS`user_info` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `type_idx` (`type`),
  KEY `group_idx` (`group_id`),
  CONSTRAINT `group` FOREIGN KEY (`group_id`) REFERENCES `group_set` (`group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `type` FOREIGN KEY (`type`) REFERENCES `limit_set` (`type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `book_in_set` (
  `id` int(11) NOT NULL,
  `morin` datetime DEFAULT NULL,
  `morout` datetime DEFAULT NULL,
  `afterin` datetime DEFAULT NULL,
  `afterout` datetime DEFAULT NULL,
  `timeTag` VARCHAR(255) DEFAULT NULL,
  CONSTRAINT `id` FOREIGN KEY (`id`) REFERENCES `user_info` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;