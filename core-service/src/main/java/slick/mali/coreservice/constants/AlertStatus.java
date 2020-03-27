package slick.mali.coreservice.constants;

/**
 * This class holds the all alerts statuses
 */
public final class AlertStatus {
    

    /**
     * the following represents the alerts journey
     * 0. New Alert Created
     * 1. Alert has been queued by scheduler
     * 2. Alert has failed
     * 3. Alert has been sent
     */
    public static final Integer NEW = 0;
    public static final Integer QUEUED = 1;
    public static final Integer FAILED = 2;
    public static final Integer DELIVERED = 3;
}