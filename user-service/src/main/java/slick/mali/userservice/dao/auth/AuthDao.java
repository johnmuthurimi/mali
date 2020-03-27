package slick.mali.userservice.dao.auth;

import java.sql.SQLException;

import slick.mali.coreservice.dao.BaseDao;
import slick.mali.coreservice.model.Auth;

public interface AuthDao  extends BaseDao {
   
   /**
    * This function returns user authentication
    * @param id user identifier
    * @return Auth object
    * @throws SQLException
    */
   public Auth getAuth(String id);  

   /**
    * This function registers new user
    * @param auth
 * @throws SQLException
    */
   public String register(Auth auth);
}