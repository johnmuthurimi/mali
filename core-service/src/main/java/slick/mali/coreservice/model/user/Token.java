package slick.mali.coreservice.model.user;

import slick.mali.coreservice.model.BaseModel;

/**
 * Domain class for user
 */
public class Token extends BaseModel {

    /**
     * User id
     */
    private String userId;

    /**
     * verified status
     */
    private Boolean verified;

    /**
     * Get user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Set the user id
     */
    public void setUserId(String id) {
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

}