package slick.mali.userservice.dao;

import slick.mali.coreservice.dao.BaseDao;
import slick.mali.coreservice.model.User;

public interface UserDao extends BaseDao {
   
   /**
    * This function returns user authentication
    * @param id user identifier
    * @return Auth object
    */
   User getUser(String id);

   /**
    * This function registers new user
    * @param user
    */
   String signUp(User user);
}