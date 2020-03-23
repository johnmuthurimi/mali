package slick.mali.userservice.dao.auth;

import java.sql.SQLException;

import slick.mali.userservice.model.Auth;

public interface AuthDao {
   
   /**
    * This function returns user authentication
    * @param id user identifier
    * @return Auth object
    * @throws SQLException
    */
   public Auth getAuth(String id) throws SQLException;  

   /**
    * This function registers new user
    * @param auth
 * @throws SQLException
    */
   public void register(Auth auth) throws SQLException;
}