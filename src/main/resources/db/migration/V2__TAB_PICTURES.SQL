CREATE TABLE `tb002_pictures` (
  `id` binary(16) NOT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  `fileType` varchar(255) DEFAULT NULL,
  `userid` binary(16) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (userid) REFERENCES tb001_users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;