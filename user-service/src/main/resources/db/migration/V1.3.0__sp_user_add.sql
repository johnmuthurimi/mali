USE user_service;

DELIMITER //
DROP PROCEDURE IF EXISTS sp_user_add;
//

DELIMITER //
CREATE PROCEDURE sp_user_add(
      IN type NVARCHAR(50),
      IN email NVARCHAR(200),
      IN identifier NVARCHAR(200),
      IN value VARCHAR(1024),
      IN status INT(10),
      IN salt NVARCHAR(200),
      IN outRecordId INT,
      IN outMessage VARCHAR(20))
BEGIN

  
  DECLARE id BIGINT DEFAULT 0;
  
  START TRANSACTION;
  -- Insert user data
  INSERT INTO user_hash(type, email, identifier, value, last_attempt, expire_date, status, salt)
  VALUES(type, email, identifier, value, UTC_DATE(), DATE_ADD(UTC_DATE(), INTERVAL 1 YEAR), status, salt);
  
  -- get user id
  SET id = LAST_INSERT_ID();
  
  -- validate inserted record
  IF id > 0 THEN
      SELECT id INTO outRecordId;
      SELECT 'success' INTO outMessage;
      -- commit
      COMMIT;
  ELSE
    ROLLBACK;
  END IF;

END //
DELIMITER ;