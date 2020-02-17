package slick.mali.userservice.constants;

/**
 * This class holds the all user statuses
 */
public final class UserStatus {
    

    /**
     * the following represents the customer journey
     * 1. User registered pending email confirmation
     * 2. User has confirmed there email address
     * 3. User approved back office
     * 4. User rejected at back office
     * 5. User failed registeration at some point
     */
    public static final Integer PENDING = 1;
    public static final Integer CONFIRMED = 2;
    public static final Integer APPROVED = 3;
    public static final Integer REJECTED = 4;
    public static final Integer FAILED = 4;
}