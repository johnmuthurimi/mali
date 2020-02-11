package slick.mali.user.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import slick.mali.user.constants.UserStatus;
import slick.mali.user.model.AuthParam;
import slick.mali.user.model.User;
import slick.mali.user.repository.UserRepository;
import slick.mali.user.util.PasswordEncoder;

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
        return (List<User>) repository.userFetch(pageNumber);
    }

    
    /**
     * This feature is responsible for the following;
     * 1. Add user succesfully to database
     * 2. Send email request to alert service via rabbitmq
     * Send notification to alert-service to send email to the customer
     * 
     * @param User
     * @return Users
     */
    @Override 
    public User signUp(User user) {
        User res = new User();
        
        try {
            // generate password
            AuthParam params = new PasswordEncoder().generateStrongPasswordHash(user.getPassword());
            user.setType(params.getType());
            user.setValue(params.getPassword());
            user.setStatus(UserStatus.PENDING);

            // save user
            res = repository.userAdd(user);
            
            // send notification to rabbitmqve not saved

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            // This failure is not bad because we ha
            new Error(ex.getMessage());
        } catch (Exception e) {
            // Here we need to consider roll back
            throw e;
        }          
        return res;
    }
}