package slick.mali.user.service;

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
     * This function returns data from the databases
     */
    @Override
    public List<User> getUsers(Long page, Long row) {
        return (List<User>) repository.getUsers(page, row);
    }
}