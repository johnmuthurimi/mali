package slick.mali.coreservice.model.user;

import slick.mali.coreservice.model.BaseModel;

/**
 * Domain class for user
 */
public class OTP extends BaseModel {

    /**
     * User id
     */
    private String userId;

    /**
     * verified status
     */
    private Boolean verified;

    /**
     * OTP number
     */
    private String otp;

    /**
     * Get user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Set the user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Get the deleted status
     */
    public Boolean isVerified() {
        return verified;
    }

    /**
     * Set the deleted status
     */
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    /**
     * Get user otp
     */
    public String getOtp() {
        return otp;
    }

    /**
     * Set the user otp
     */
    public void setOtp(String otp) {
        this.otp = otp;
    }

}