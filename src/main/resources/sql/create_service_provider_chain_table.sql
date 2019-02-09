CREATE TABLE `spchain` (
  `id` varchar(255) NOT NULL AUTO_INCREMENT,
  `provider_id` int(11) DEFAULT NULL,
  `service_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;