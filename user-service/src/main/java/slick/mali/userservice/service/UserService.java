package slick.mali.userservice.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slick.mali.userservice.constants.UserStatus;
import slick.mali.userservice.model.AuthParam;
import slick.mali.userservice.model.User;
import slick.mali.userservice.repository.UserRepository;
import slick.mali.userservice.util.PasswordUtils;


/**
 * Implementation for all user operations
 */
@Service
public class UserService implements IUserService {

    /**
     * Inject the user repository
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * This functions gets all users You must however the page and number of items
     * in the page By default the system gives 100 users per page
     */
    @Override
    public List<User> userFetch(int pageNumber) {
        return (List<User>) userRepository.findAll();
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
            // return userRepository.save(user);         
            // send notification to rabbitmqve not saved
            return res;
        } catch (Exception e) {
            // Here we need to consider roll back
            throw e;
        }          
    }
}