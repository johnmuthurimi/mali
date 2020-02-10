USE user_service;

CREATE TABLE user_hash (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  type NVARCHAR(50) NOT NULL,
  params NVARCHAR(200) NOT NULL,
  email NVARCHAR(200) NOT NULL,
  identifier NVARCHAR(200) NOT NULL,
  value VARCHAR(1024) NOT NULL,
  failed_attempts INT DEFAULT 0 NOT NULL,
  last_attempt DATE NOT NULL,
  expire_date DATE NOT NULL,
  enabled TINYINT(1) DEFAULT 1 NOT NULL,
  deleted TINYINT(1) DEFAULT 0 NOT NULL,
  UNIQUE KEY UK_user_hash_identifier (identifier),
  UNIQUE KEY UK_user_hash_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
