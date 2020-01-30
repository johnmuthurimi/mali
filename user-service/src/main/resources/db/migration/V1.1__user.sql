USE user_service;

CREATE TABLE user (
  id binary(16) PRIMARY KEY),
  username varchar(100) NOT NULL,
  first_name varchar(50) DEFAULT NULL,
  last_name varchar(50) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;