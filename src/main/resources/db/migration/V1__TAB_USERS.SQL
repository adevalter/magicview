CREATE TABLE `tb001_users` (
  `id` binary(16) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nick` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;