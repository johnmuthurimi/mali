USE user_service;

DROP TABLE IF EXISTS user_verify;

CREATE TABLE user_verify (
    id 		        VARCHAR(36) NOT NULL,
    user_id 		    VARCHAR(36) NOT NULL,
    token 		    VARCHAR(36) NOT NULL,
    verified        tinyint(1) NOT NULL DEFAULT 0,
    deleted		    tinyint(1) NOT NULL DEFAULT 0,

    PRIMARY KEY (id),
    INDEX (user_id),
    CONSTRAINT unique_user_verify_id UNIQUE (id),
    CONSTRAINT unique_user_verify_token UNIQUE (token),
    FOREIGN KEY (user_id) REFERENCES user_user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;