USE user_service;

DELIMITER //
DROP PROCEDURE IF EXISTS sp_user_fetch;
//

DELIMITER //
CREATE PROCEDURE sp_user_fetch(IN pageNumber int)
BEGIN
  DECLARE records INT DEFAULT 30;
  DECLARE offset INT DEFAULT 1;

  SET @offset = (pageNumber*records);

  SELECT * FROM user LIMIT offset, records;

END //
DELIMITER ;