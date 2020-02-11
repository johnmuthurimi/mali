USE user_service;

ALTER TABLE user_hash ADD status INT(10) NOT NULL;
ALTER TABLE user_hash DROP COLUMN params;