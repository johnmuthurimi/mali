USE user_service;

CREATE TABLE user (
  id binary(16) PRIMARY KEY,
  username varchar(100) NOT NULL,
  firstname varchar(50) DEFAULT NULL,
  lastname varchar(50) DEFAULT NULL,
  password binary(100) NOT NULL,
  email varchar(100) NOT NULL,
  enabled TINYINT(1) DEFAULT 1 NOT NULL,
  deleted TINYINT(1) DEFAULT 0 NOT NULL,
  UNIQUE KEY UK_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
