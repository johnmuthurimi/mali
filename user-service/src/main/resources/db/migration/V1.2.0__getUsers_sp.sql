USE user_service;

DROP PROCEDURE IF EXISTS sp_getUsers;

DELIMITER //
CREATE DEFINER = 'root'@'%' PROCEDURE sp_getUsers(page int, row int)
BEGIN
  SELECT * FROM user;
END //
DELIMITER ;