package slick.mali.userservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slick.mali.coreservice.constants.UserStatus;
import slick.mali.coreservice.model.User;
import slick.mali.coreservice.util.LoggerUtil;
import slick.mali.userservice.dao.UserDao;
import slick.mali.coreservice.model.EventRequest;
import slick.mali.userservice.rabbitmq.EventMessageSender;
import slick.mali.userservice.rabbitmq.RabbitConfig;
import slick.mali.coreservice.util.PasswordUtils;

/**
 * Implementation for all user operations
 */
@Service
public class UserServiceImpl implements IUserService {

    public  static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private EventMessageSender eventMessageSender;

    /**
     * Inject the user repository
     */
    @Autowired
    private UserDao userDao;

    /**
     * This functions user credentials
     */
    @Override
    public User getUser(final String id) {
        return userDao.getUser(id);
    }

    /**
     * This feature is responsible for the following;
     * 1. Add user successfully to database
     * 2. Send email request to alert service via rabbitmq Send
     * notification to alert-service to send email to the customer
     * 
     * @param user
     * @return Users
     */
    @Override
    public User signUp(final User user) {
        try {
            LoggerUtil.info(LOGGER, "AlertServiceImpl: Initiate user sign up for: " + user.getEmail());
            // generate salts
            final String salt = PasswordUtils.getSalt(30);
            final String hashValue = PasswordUtils.generateSecurePassword(user.getPassword(), salt);
            user.setType("password");
            user.setSalt(salt);
            user.setPassword(hashValue);
            user.setStatus(UserStatus.NEW);
            user.setEnabled(false);
            user.setDeleted(false);
            LoggerUtil.info(LOGGER, "AlertServiceImpl: Generated password for: " + user.getEmail());

            // Register users
            final String id = userDao.signUp(user);
            user.setId(id);
            LoggerUtil.info(LOGGER, "AlertServiceImpl: Insert the user in the database: " + user.getEmail());

            // send notification to rabbitmq
            final EventRequest event = new EventRequest();
            event.setId(user.getId());
            event.setEmail(user.getEmail());
            event.setusername(user.getusername());
            sendMessage(event);
            LoggerUtil.info(LOGGER, "AlertServiceImpl: Initiated RabbitMQ OTP request for: " + user.getEmail());
        } catch (Exception e) {
            // We need to delete user from the database if exist
            LoggerUtil.error(LOGGER, "AlertServiceImpl: There experienced error registering user : " + user.getEmail());
            LoggerUtil.error(LOGGER, e.getMessage());
        }

        LoggerUtil.info(LOGGER, "AlertServiceImpl: Completed sign up requested for : " + user.getEmail());
        return user;
    }

    /**
     * Send message request via Rabbit MQ
     */
    @Override
    public void sendMessage(final EventRequest event) {
        eventMessageSender.sendMessage(event, RabbitConfig.QUEUE_EMAIL_VERIFICATION);
    }
}