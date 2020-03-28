package slick.mali.coreservice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Use this class for date utilities
 */
public class DateUtils {
    
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Generate date now
     * 
     * @return String
     */
    public static Date now() {
        try {
            Date now = new Date();
            String strDate = dateFormat.format(now);
            Date date = dateFormat.parse(strDate);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }
}