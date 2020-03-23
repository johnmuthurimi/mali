package slick.mali.userservice.dao.auth;

import slick.mali.userservice.model.Auth;

public interface AuthDao {
   
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