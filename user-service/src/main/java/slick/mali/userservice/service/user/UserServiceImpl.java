package slick.mali.userservice.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slick.mali.coreservice.constants.UserStatus;
import slick.mali.coreservice.model.EventRequest;
import slick.mali.coreservice.model.user.User;
import slick.mali.coreservice.util.LoggerUtil;
import slick.mali.coreservice.util.PasswordUtils;
import slick.mali.userservice.dao.otp.OtpDao;
import slick.mali.userservice.dao.user.UserDao;
import slick.mali.userservice.rabbitmq.EventMessageSender;
import slick.mali.userservice.rabbitmq.RabbitConfig;

/**
 * Implementation for all user operations
 */
@Service
public class UserServiceImpl implements IUserService {

    public  static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private EventMessageSender eventMessageSender;

    /**
     * Inject the user DAO
     */
    @Autowired
    private UserDao userDao;

    /**
     * Inject the otp DAO
     */
    @Autowired
    private OtpDao otpDao;


    /**
     * Send message request via Rabbit MQ
     */
    public void sendOTPRequest(final EventRequest event) {
        eventMessageSender.sendMessage(event, RabbitConfig.QUEUE_OTP);
    }

    /**
     * Check if user exist
     * @param user
     * @return
     */
    public boolean userExists(User user) {
        boolean exist = false;
        User checkUser = userDao.findByEmail(user.getEmail());
        if (checkUser != null) {
            exist = true;
        }

        return exist;
    }

    /**
     * Find user by Id
     * @param id
     * @return
     */
    @Override
    public User findById(final String id) {
        return userDao.findById(id);
    }

    /**
     * This feature is responsible for the following;
     * 1. Add user successfully to database
     * 2. Send email request to alert service via rabbitMQ Send
     * notification to alert-service to send email to the customer
     *
     * @param user
     * @return Users
     */
    @Override
    public User create(final User user) throws Exception {
        final EventRequest event = new EventRequest();
        boolean found = userExists(user);
        if (!found) {
            try {
                LoggerUtil.info(LOGGER, " Initiate user register for: " + user.getEmail());
                // generate salts
                final String salt = PasswordUtils.getSalt(30);
                final String hashValue = PasswordUtils.generateSecurePassword(user.getPassword(), salt);
                user.setSalt(salt);
                user.setPassword(hashValue);
                user.setStatus(UserStatus.NEW);
                user.setEnabled(false);
                user.setDeleted(false);
                LoggerUtil.info(LOGGER, "Generated password for: " + user.getEmail());

                // Register users
                final String id = userDao.create(user);
                user.setId(id);
                LoggerUtil.info(LOGGER, "Insert the user in the database: " + user.getEmail());

                LoggerUtil.info(LOGGER, "Generate user verification OTP for : " + user.getEmail());
                final String otp = otpDao.create(user.getId());
                user.setId(id);

                event.setFirstName(user.getFirstName());
                event.setId(user.getId());
                event.setEmail(user.getEmail());
                event.setOtp(otp);

                // send notification to rabbitmq
                sendOTPRequest(event);
                LoggerUtil.info(LOGGER, "Initiated RabbitMQ OTP request for: " + user.getEmail());

                // Update user to pending
                userDao.updateStatus(UserStatus.PENDING, user.getId());
                LoggerUtil.info(LOGGER, "Pending user OTP confirmation for: " + user.getEmail());
            } catch (Exception e) {
                // We need to delete user OTP from the database if exist
                if (event.getOtp() != null) {
                    otpDao.delete(event.getOtp(), user.getId());
                }

                // We need to delete user from the database if exist
                if (user.getId() != null) {
                    userDao.delete(user.getId());
                }

                LoggerUtil.error(LOGGER, " There experienced error registering user : " + user.getEmail());
                LoggerUtil.error(LOGGER, e.getMessage());
                throw e;
            }

            LoggerUtil.info(LOGGER, "Completed sign up requested for : " + user.getEmail());
            return user;
        } else {
            LoggerUtil.error(LOGGER, "User already exists! : " + user.getEmail());
            throw new Exception("User already exists!");
        }
    }

    /**
     * Get user by email
     * @param email
     * @return
     */
    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

}