USE user_service;

DROP TABLE IF EXISTS otps;

CREATE TABLE otps (
    id 		        VARCHAR(36) NOT NULL,
    user_id 		VARCHAR(36) NOT NULL,
    otp             VARCHAR(10) NOT NULL,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by      VARCHAR(36) NOT NULL,
    updated_at      TIMESTAMP NULL,
    updated_by      VARCHAR(36) NULL,
    verified        tinyint(1) NOT NULL DEFAULT 0,
    deleted		    tinyint(1) NOT NULL DEFAULT 0,

    PRIMARY KEY (id),
    INDEX (otp, deleted),
    INDEX (id, deleted),
    FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;