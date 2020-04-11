package slick.mali.userservice.dao.user;

import slick.mali.coreservice.dao.BaseDao;
import slick.mali.coreservice.model.user.User;

public interface UserDao extends BaseDao {


   /**
    * Find user by email
    * @param email
    * @return user
    */
   User findByEmail(String email);

   /**
    * Find user by ID which is the unique identifier
    * @param id
    * @return
    */
   User findById(String id);

   /**
    * Create user in the database
    * @param user
    * @return
    */
   String create(User user);

   /**
    * Delete user from the database
    * @param id
    */
   void delete(String id);

   /**
    * Updated user status
    * @param status
    * @param userId
    */
   void updateStatus(Integer status, String userId);

   /**
    * Update user is verified
    * @param userId
    */
   void updateVerified(String userId);
}