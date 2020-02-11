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
}