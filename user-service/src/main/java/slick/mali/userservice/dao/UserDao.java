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

   /**
    * Update user pending
    * @param userId
    */
   void updateUserPending(String userId);

   /**
    * Update user verified
    * @param userId
    */
   void updateUserVerified(String userId);

   /**
    * Delete user completely from the database
    * @param id
    */
   void deleteUser(String id);

   /**
    * This function to create user token for verification
    * @param user
    */
   String generateUserVerificationToken(User user);

   /**
    *  Get user token using the token Id
    * @param tokenId
    * @return
    */
   User getToken(String tokenId);

   /**
    * Update token as verified
    * @param tokenId
    */
   void updateTokenVerified(String tokenId);

   /**
    * Delete token completely from the database
    * @param id
    */
   void deleteToken(String id);
}