USE user_service;

CREATE TABLE user_hash (
  id INT AUTO_INCREMENT PRIMARY KEY,
  type VARCHAR(10) NOT NULL,
  salt VARCHAR(50) NOT NULL,
  email NVARCHAR(50) NOT NULL,
  identifier NVARCHAR(20) NOT NULL,
  value VARCHAR(512) NOT NULL,
  status INT(10) NOT NULL,
  failed_attempts INT(10) DEFAULT 0 NOT NULL,
  last_attempt DATE NOT NULL,
  expire_date DATE NOT NULL,
  enabled TINYINT(1) DEFAULT 1 NOT NULL,
  deleted TINYINT(1) DEFAULT 0 NOT NULL,
  UNIQUE KEY UK_user_hash_identifier (identifier),
  UNIQUE KEY UK_user_hash_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
