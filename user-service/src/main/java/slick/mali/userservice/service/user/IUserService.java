package slick.mali.userservice.service.user;

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
     * Login User to the platform
     * @param login
     * @return
     */
    User login(User login);
}