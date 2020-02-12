package slick.mali.user.model;

/**
 * Class holds params required in authentication
 */
public class AuthParam {

    /**
     * Authentication type
     */
    private String type;

    /**
     * Password
     */
    private String password;

    /**
     * salt
     */
    private String salt;

    /**
     * Get type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Set the salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }
}