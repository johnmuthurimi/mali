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
     * Check if user exist
     * @param user
     * @return
     */
    public boolean checkUserExists(User user) {
        boolean exist = false;
        User checkUser = userDao.getUserByEmail(user.getEmail());
        if (checkUser != null) {
            exist = true;
        }

        return exist;
    }

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
        final EventRequest event = new EventRequest();
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

            LoggerUtil.info(LOGGER, "AlertServiceImpl: Generate user verification token for : " + user.getEmail());
            final String token = userDao.generateUserVerificationToken(user);
            user.setId(id);

            event.setId(user.getId());
            event.setEmail(user.getEmail());
            event.setToken(token);

            // send notification to rabbitmq
            sendMessage(event);
            LoggerUtil.info(LOGGER, "AlertServiceImpl: Initiated RabbitMQ OTP request for: " + user.getEmail());

            // Update user to pending
            userDao.updateUserPending(user.getId());
            LoggerUtil.info(LOGGER, "AlertServiceImpl: Pending user email confirmation for: " + user.getEmail());
        } catch (Exception e) {
            // We need to delete user token from the database if exist
            if (event.getToken() != null) {
                userDao.deleteToken(event.getToken());
            }

            // We need to delete user from the database if exist
            if (user.getId() != null) {
                userDao.deleteUser(user.getId());
            }

            LoggerUtil.error(LOGGER, "AlertServiceImpl: There experienced error registering user : " + user.getEmail());
            LoggerUtil.error(LOGGER, e.getMessage());
            throw e;
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

    /**
     * Verify the user token whether it is valid
     * @param token
     * @return
     */
    @Override
    public User isTokenValid(String token) throws Exception {
        User user = new User();

        try {
            user = userDao.getToken(token);
            if (user != null && user.getToken() != null) {
                if (user.isVerified()) {
                    throw new Exception("Token has already been used");
                } else {

                    // Update the token as verified
                    userDao.updateTokenVerified(user.getToken());

                    // Update the user as verified, enable the user and change status
                    userDao.updateUserVerified(user.getId());
                }
            }
        } catch(Exception e) {
            throw new Exception("Token has already been used");
        }

        return user;
    }
}