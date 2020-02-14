package slick.mali.user.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slick.mali.user.constants.UserStatus;
import slick.mali.user.model.AuthParam;
import slick.mali.user.model.User;
import slick.mali.user.repository.UserRepository;
import slick.mali.user.util.PasswordUtils;


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
            // generate salts
            String salt = PasswordUtils.getSalt(30);        
            // Protect user's password. The generated value can be stored in DB.
            AuthParam params = PasswordUtils.generateSecurePassword(user.getPassword(), salt);
            user.setType(user.getType());
            user.setValue(params.getPassword());
            user.setSalt(salt);
            user.setStatus(UserStatus.PENDING);

            // save user
            res = repository.userAdd(user);            
            // send notification to rabbitmqve not saved
            return res;
        } catch (Exception e) {
            // Here we need to consider roll back
            throw e;
        }          
    }
}