package slick.mali.user.service;

import java.util.List;
import slick.mali.user.model.User;
import slick.mali.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

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
     * This function returns data from the databases
     */
    @Override
    public List<User> getUsers(Long page, Long row) {
        return (List<User>) repository.getUsers(page, row);
    }
}