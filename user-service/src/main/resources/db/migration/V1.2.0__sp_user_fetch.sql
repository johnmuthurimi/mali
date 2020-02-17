USE user_service;

DELIMITER //
DROP PROCEDURE IF EXISTS sp_user_fetch;
//

DELIMITER //
CREATE PROCEDURE sp_user_fetch(IN pageNumber int)
BEGIN
  DECLARE records INT DEFAULT 30;
  DECLARE offset INT DEFAULT 1;

  SET @offset = fn_pagination_offset(pageNumber);

  SELECT id, email, identifier, enabled, deleted, status FROM user_hash LIMIT offset, records;

END //
DELIMITER ;