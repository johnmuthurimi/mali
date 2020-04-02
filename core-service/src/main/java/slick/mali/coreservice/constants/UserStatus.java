package slick.mali.coreservice.constants;

/**
 * This class holds the all user statuses
 */
public final class UserStatus {
    

    /**
     * the following represents the customer journey
     * 0. User created but without notification
     * 1. User registered pending email confirmation
     * 2. User has verified there email address
     * 3. User locked because of password attempt failure
     * 4. User deactivated at back office
     */
    public static final Integer NEW = 0;
    public static final Integer PENDING = 1;
    public static final Integer ACTIVE = 2;
    public static final Integer LOCKED = 3;
    public static final Integer DEACTIVATED = 4;
}