CREATE TABLE `tb002_pictures` (
  `id` binary(16) NOT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  `fileType` varchar(255) DEFAULT NULL,
  `userid` binary(16) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (userid) REFERENCES tb001_users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;