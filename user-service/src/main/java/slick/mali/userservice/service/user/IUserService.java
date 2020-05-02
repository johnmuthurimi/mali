package slick.mali.userservice.service.user;

import slick.mali.coreservice.model.user.OTP;
import slick.mali.coreservice.model.user.User;
import slick.mali.coreservice.service.IBaseService;


/**
 * Interface for all user operations
 */
public interface IUserService extends IBaseService {

    /**
     * find user by id
     * @param id
     * @return
     */
    User findById(String id);

    /**
     * This feature is responsible for registering a user
     * 
     * @param user
     * @return Users
     * @throws Exception
     */
    User create(User user) throws Exception;

    /**
     * Get user by email
     * @param username
     * @return
     */
    User findByEmail(String email);
}