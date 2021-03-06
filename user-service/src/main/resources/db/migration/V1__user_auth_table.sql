USE user_service;

DROP TABLE IF EXISTS user_auth;

CREATE TABLE user_auth (
    id 		        VARCHAR(36) NOT NULL,
    type 		    CHAR(8) NOT NULL DEFAULT 'password',
    salt 		    VARCHAR(50) NOT NULL,
    email 	        VARCHAR(50) NOT NULL,
    username 		VARCHAR(50) NOT NULL,
    password	    VARCHAR(512) NOT NULL,
    status 		    INT(1) NOT NULL DEFAULT 0,
    enabled 		tinyint(1) DEFAULT 0,
    deleted		    tinyint(1) DEFAULT 0,

    PRIMARY KEY (id),
    CONSTRAINT unique_user_auth_id UNIQUE (id),
    CONSTRAINT unique_user_auth_email UNIQUE (email),
    CONSTRAINT unique_user_auth_username UNIQUE (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;