package slick.mali.coreservice.util;

import org.slf4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Logger Util for all request
 */
public class LoggerUtil {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Create infomation log
     * @param logger
     * @param message
     */
    public static void info(Logger logger, String message) {
        Date now = new Date();
        String strDate = dateFormat.format(now);
        logger.info(strDate + " - " + message);
    }

    /**
     * Create error log
     * @param logger
     * @param message
     */
    public static void error(Logger logger, String message) {
        Date now = new Date();
        String strDate = dateFormat.format(now);
        logger.error(strDate + " - " + message);
    }

    /**
     * Create trace log
     * @param logger
     * @param message
     */
    public static void trace(Logger logger, String message) {
        Date now = new Date();
        String strDate = dateFormat.format(now);
        logger.trace(strDate + " - " + message);
    }

    /**
     * Create warn log
     * @param logger
     * @param message
     */
    public static void warn(Logger logger, String message) {
        Date now = new Date();
        String strDate = dateFormat.format(now);
        logger.warn(strDate + " - " + message);
    }

    /**
     * Create debug log
     * @param logger
     * @param message
     */
    public static void debug(Logger logger, String message) {
        Date now = new Date();
        String strDate = dateFormat.format(now);
        logger.debug(strDate + " - " + message);
    }
    
}