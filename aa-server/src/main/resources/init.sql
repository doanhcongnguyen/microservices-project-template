CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `is_deleted` bigint DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user`(id, full_name, username, created_date, created_by, password, is_deleted) VALUES (1, 'admin', 'admin', now(), 'supperadmin', '$2a$10$N9UHXAV3BUmQO1WuGYGHDe1DqyN0GXnBfsbHcec.HtWpsukr2TfAq', 0);