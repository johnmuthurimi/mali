package slick.mali.userservice.service.otp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slick.mali.coreservice.constants.UserStatus;
import slick.mali.coreservice.model.EventRequest;
import slick.mali.coreservice.model.user.OTP;
import slick.mali.coreservice.model.user.User;
import slick.mali.coreservice.util.LoggerUtil;
import slick.mali.userservice.dao.otp.OtpDao;
import slick.mali.userservice.dao.user.UserDao;
import slick.mali.userservice.rabbitmq.EventMessageSender;
import slick.mali.userservice.rabbitmq.RabbitConfig;

import java.util.concurrent.TimeUnit;

/**
 * Implementation for all user operations
 */
@Service
public class OtpServiceImpl implements IOtpService {

    public  static final Logger LOGGER = LoggerFactory.getLogger(OtpServiceImpl.class);

    @Autowired
    private EventMessageSender eventMessageSender;

    /**
     * Inject the OTP DAO
     */
    @Autowired
    private OtpDao otpDao;

    /**
     * Inject the user DAO
     */
    @Autowired
    private UserDao userDao;

    /**
     * Verify the user otp whether it is valid
     * @param otp
     * @return
     */
    @Override
    public OTP verifyOTP(String otp) throws Exception {
        try {
            LoggerUtil.info(LOGGER, "Fetching OTP: " + otp);
            OTP result = otpDao.findByOtp(otp);
            if (result != null) {
                long now = System.currentTimeMillis(); // See note below
                long then = result.getCreatedAt().getTime();
                long minutes = TimeUnit.MILLISECONDS.toMinutes(now - then);

                if (result.isVerified()) {
                    LoggerUtil.error(LOGGER, "OTP " + otp + " has already been used!");
                    throw new Exception("OTP has already  been used!");
                } else if(minutes > 15) {
                    LoggerUtil.error(LOGGER, "OTP " + otp + " has already EXPIRED!");
                    throw new Exception("OTP has already EXPIRED!");
                } else {
                    // Update the OTP as verified
                    userDao.updateVerified(result.getUserId());

                    // Update the user as verified, enable the user and change status
                    otpDao.updateVerified(result.getId());
                    LoggerUtil.info(LOGGER, "Successfully validated OTP: " + otp);
                }

                return result;
            } else {
                LoggerUtil.error(LOGGER, "OTP " + otp + " is invalid");
                throw new Exception("OTP is invalid");
            }
        } catch(Exception e) {
            LoggerUtil.error(LOGGER, "OTP " + otp + " is invalid");
            throw new Exception("OTP is invalid");
        }
    }

    /**
     * Created otp
     *
     * @param userId
     * @return OTP
     */
    @Override
    public String create(String userId) throws Exception {
        final EventRequest event = new EventRequest();
        User checkUser = userDao.findById(userId);
        if (checkUser != null) {
            LoggerUtil.info(LOGGER, "Invalidate all user OTP for : " + checkUser.getEmail());
            otpDao.deleteByUserId(userId);

            LoggerUtil.info(LOGGER, "Generate user verification OTP for : " + checkUser.getEmail());
            final String otp = otpDao.create(userId);

            event.setFirstName(checkUser.getFirstName());
            event.setId(checkUser.getId());
            event.setEmail(checkUser.getEmail());
            event.setOtp(otp);

            // send notification to rabbitmq
            eventMessageSender.sendMessage(event, RabbitConfig.QUEUE_OTP);
            LoggerUtil.info(LOGGER, "Initiated RabbitMQ OTP request for: " + checkUser.getEmail());

            // Update user to pending
            userDao.updateStatus(UserStatus.PENDING, checkUser.getId());
            LoggerUtil.info(LOGGER, "Pending user OTP confirmation for: " + checkUser.getEmail());
            return otp;
        } else {
            LoggerUtil.info(LOGGER, "User does not exists! : " + checkUser.getEmail());
            throw new Exception("User does not exists!");
        }
    }
}