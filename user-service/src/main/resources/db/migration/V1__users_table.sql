USE user_service;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id 		        VARCHAR(36) NOT NULL,
    first_name 	    VARCHAR(20) NOT NULL,
    last_name 	    VARCHAR(20) NOT NULL,
    email 	        VARCHAR(50) NOT NULL,
    password	    VARCHAR(512) NOT NULL,
    salt 		    VARCHAR(50) NOT NULL,
    status 		    INT(1) NOT NULL DEFAULT 0,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by      VARCHAR(36) NOT NULL,
    updated_at      TIMESTAMP NULL,
    updated_by      VARCHAR(36) NULL,
    verified        tinyint(1) NOT NULL DEFAULT '0',
    enabled 		tinyint(1) NOT NULL DEFAULT 0,
    deleted		    tinyint(1) NOT NULL DEFAULT 0,

    PRIMARY KEY (id),
    INDEX (id, deleted),
    INDEX (email, deleted),
    INDEX (email, enabled, deleted),
    CONSTRAINT unique_user_user_id UNIQUE (id),
    CONSTRAINT unique_user_user_email UNIQUE (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;