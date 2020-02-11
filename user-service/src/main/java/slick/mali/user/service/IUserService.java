package slick.mali.user.service;

import java.util.List;
import slick.mali.user.model.User;

/**
 * Interface for all user operations
 */
public interface IUserService extends IBaseService {

    /**
     * This functions gets all users You must however the page and number of items
     * in the page By default the system gives 100 users per page
     * 
     * @param pageNumber
     * @return List of users
     */
    List<User> userFetch(int pageNumber);

    /**
     * This feature is responsible for registering a customer
     * 
     * @param User
     * @return Users
     */
    User signUp(User user);
}