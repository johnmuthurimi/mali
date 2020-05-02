package slick.mali.userservice.dao.otp;

import slick.mali.coreservice.dao.BaseDao;
import slick.mali.coreservice.model.user.OTP;
import slick.mali.coreservice.model.user.User;

public interface OtpDao extends BaseDao {

   /**
    * Created otp
    * @param userId
    * @return OTP
    */
   String create(String userId);

   /**
    * find by OTP
    * @param otp
    * @return
    */
   OTP findByOtp(String otp);

   /**
    * Update OTP as verified
    * @param otpId
    */
   void updateVerified(String otpId);

   /**
    * Delete OTP completely from the database
    * @param id
    * @param userId
    */
   void delete(String id, String userId);

   /**
    * Delete OTP completely from the database
    * @param userId
    */
   void deleteByUserId(String userId);
}