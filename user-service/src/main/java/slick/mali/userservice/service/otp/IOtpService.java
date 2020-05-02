package slick.mali.userservice.service.otp;

import slick.mali.coreservice.model.user.OTP;
import slick.mali.coreservice.service.IBaseService;


/**
 * Interface for all user operations
 */
public interface IOtpService extends IBaseService {

    /**
     * Verify OTP
     * @param otp
     * @return
     * @throws Exception
     */
    OTP verifyOTP(String otp) throws Exception;

    /**
     * Created otp
     * @param userId
     * @return OTP
     */
    String create(String userId) throws Exception;
}