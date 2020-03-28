package slick.mali.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slick.mali.coreservice.constants.UserStatus;
import slick.mali.userservice.dao.auth.AuthDao;
import slick.mali.coreservice.model.Auth;
import slick.mali.coreservice.model.EventRequest;
import slick.mali.userservice.rabbitmq.EventMessageSender;
import slick.mali.userservice.rabbitmq.RabbitConfig;
import slick.mali.coreservice.util.PasswordUtils;

/**
 * Implementation for all user operations
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private EventMessageSender eventMessageSender;

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
    public Auth getAuth(final String id) {
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
    public Auth register(final Auth user) {
        // generate salts
        final String salt = PasswordUtils.getSalt(30);
        final String hashValue = PasswordUtils.generateSecurePassword(user.getPassword(), salt);
        user.setType("password");
        user.setSalt(salt);
        user.setPassword(hashValue);
        user.setStatus(UserStatus.NEW);
        user.setEnabled(false);
        user.setDeleted(false);

        // Register users
        final String id = authDao.register(user);
        user.setId(id);

        // send notification to rabbitmq
        final EventRequest event = new EventRequest();
        event.setEmail(user.getEmail());
        event.setusername(user.getusername());
        sendMesage(event);
        
        return user;
    }

    /**
     * Send message request via Rabbit MQ
     */
    @Override
    public void sendMesage(final EventRequest event) {
        eventMessageSender.sendMessage(event, RabbitConfig.QUEUE_OTP);
    }
}