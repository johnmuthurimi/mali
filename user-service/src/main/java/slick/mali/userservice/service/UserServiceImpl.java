package slick.mali.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slick.mali.userservice.constants.UserStatus;
import slick.mali.userservice.dao.auth.AuthDao;
import slick.mali.userservice.model.Auth;
import slick.mali.userservice.util.PasswordUtils;

/**
 * Implementation for all user operations
 */
@Service
public class UserServiceImpl implements IUserService {

    /**
     * Inject the user repository
     */
    @Autowired
    private AuthDao authDao;

    /**
     * This functions user credentials
     * 
     * @throws SQLException
     */
    @Override
    public Auth getAuth(String id) {
        return authDao.getAuth(id);
    }

    
    /**
     * This feature is responsible for the following; 1. Add user succesfully to
     * database 2. Send email request to alert service via rabbitmq Send
     * notification to alert-service to send email to the customer
     * 
     * @param User
     * @return Users
     * @throws Exception
     */
    @Override
    public Auth register(Auth user) {
        // generate salts
        String salt = PasswordUtils.getSalt(30);        
        String hashValue = PasswordUtils.generateSecurePassword(user.getPassword(), salt);
        user.setType("password");
        user.setSalt(salt);
        user.setPassword(hashValue);            
        user.setStatus(UserStatus.NEW);
        user.setEnabled(false);
        user.setDeleted(false);
        String id = authDao.register(user);
        user.setId(id);

        // save user  
        // return userRepository.save(user);         
        // send notification to rabbitmqve not saved
        return user;         
    }
}