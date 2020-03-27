USE alert_service;

DROP TABLE IF EXISTS alert_email_out;

CREATE TABLE alert_email_out (
    id 		        VARCHAR(36) NOT NULL,
    sender 		    VARCHAR(50) NOT NULL,
    recepient 	    VARCHAR(50) NOT NULL,
    message 		VARCHAR(2000) NOT NULL,
    createdAt       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    sentAt          TIMESTAMP NULL,
    deliveredAt     TIMESTAMP NULL,
    status 		    INT(1) NOT NULL DEFAULT 0,

    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;