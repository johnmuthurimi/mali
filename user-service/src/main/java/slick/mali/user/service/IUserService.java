package slick.mali.user.service;

import java.util.List;
import slick.mali.user.model.User;

/**
 * Interface for all user operations
 */
public interface IUserService extends IBaseService {

    /**
     * This functions gets all users
     * You must however pass a filter
     * By default the system gives 100 users per page
     */
    List<User> getUsers(Long page, Long row);
}