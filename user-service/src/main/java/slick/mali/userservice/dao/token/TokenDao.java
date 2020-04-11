package slick.mali.userservice.dao.token;

import slick.mali.coreservice.dao.BaseDao;
import slick.mali.coreservice.model.user.Token;
import slick.mali.coreservice.model.user.User;

public interface TokenDao extends BaseDao {

   /**
    * Created verification token
    * @param user
    * @return token
    */
   String create(User user);

   /**
    * find by token id
    * @param id
    * @return
    */
   Token findById(String id);

   /**
    * Update token as verified
    * @param tokenId
    */
   void updateVerified(String tokenId);

   /**
    * Delete token completely from the database
    * @param id
    */
   void delete(String id);
}