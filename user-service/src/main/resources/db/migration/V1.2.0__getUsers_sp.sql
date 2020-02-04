DELIMITER $$

USE ``user_service``$$

DROP PROCEDURE IF EXISTS `sp_getUsers`$$

CREATE DEFINER=`root`@`%` PROCEDURE `sp_getUsers`([page] int, [row] int)
BEGIN
  select * FROM user;
END$$
 
DELIMITER ;