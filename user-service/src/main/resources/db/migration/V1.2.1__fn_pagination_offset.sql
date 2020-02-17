USE user_service;

DELIMITER //
DROP FUNCTION IF EXISTS fn_pagination_offset;
//


DELIMITER //

CREATE FUNCTION fn_pagination_offset ( pageNumber INT )
RETURNS INT

BEGIN

  DECLARE found TINYINT;
  DECLARE offset INT;
  DECLARE x INT;
   

  SET offset = 0;
  SET found = 0;
  SET x = 1;

  WHILE found = 0 DO
    IF x > pageNumber 
      THEN 
        SET found = 1;
        SET offset = (x * pageNumber);
      END IF;
    SET x=x+1;
  END WHILE;

  RETURN offset;

END; //

DELIMITER ;