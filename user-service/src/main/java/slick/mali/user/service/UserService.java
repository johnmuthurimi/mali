package slick.mali.user.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slick.mali.user.model.User;
import slick.mali.user.repository.UserRepository;

/**
 * Implementation for all user operations
 */
@Service
public class UserService implements IUserService {

    /**
     * Inject the user repository
     */
    @Autowired
    private UserRepository repository;

    /**
     * This functions gets all users You must however the page and number of items
     * in the page By default the system gives 100 users per page
     */
    @Override
    public List<User> userFetch(int pageNumber) {
        // Atempt fetching users
        List<User> userResponse = null;
        try {
            userResponse = (List<User>) repository.userFetch(pageNumber);
        } catch (Exception e) {
            throw e;
        }
        return userResponse;
    }
}