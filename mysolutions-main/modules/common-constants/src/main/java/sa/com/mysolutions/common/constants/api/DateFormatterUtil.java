package sa.com.mysolutions.common.constants.api;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class DateFormatterUtil {
	/**
     * 
     * To parse the string into Java Date object
     * 
     * @param date
     * @return Date
     */
    public static Date parseDate(String date) {
        Date parsedDate = null;
        if (Validator.isNotNull(getMatchedDateFormate(date))) {
            try {
                parsedDate = new SimpleDateFormat(getMatchedDateFormate(date)).parse(date);
            } catch (Exception e) {
                _log.error("Error while parsing the date " + date + ": " + e.getMessage());
                e.printStackTrace();
            }
        }
        return parsedDate;
    }

 

    /**
     * Get the matched date formate
     * 
     * @param date
     * @return String
     */
    public static String getMatchedDateFormate(String date) {
        String dateFormat = StringPool.BLANK;

 

        Map<String, String> datePatternMapList = new HashMap<>();
        datePatternMapList.put(DateConstants.YYYY_MM_DD, "^\\d{4}-\\d{2}-\\d{2}$");
        datePatternMapList.put(DateConstants.DD_MM_YYYY, "^\\d{2}-\\d{2}-\\d{4}$");

 

        for (Entry<String, String> patternEntry : datePatternMapList.entrySet()) {
            Pattern datePattern = Pattern.compile(patternEntry.getValue());
            if (datePattern.matcher(date).matches()) {
                return patternEntry.getKey();
            }
        }

 

        return dateFormat;
    }

 

    /**
     * To parse the string into Java Date object
     * 
     * @param date
     * @param format
     * @return Date
     */
    public static Date parseDate(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            _log.error("Error while parsing the date " + date + ": " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

 

    /**
     * Formate The date to teh given format
     * 
     * @param date
     * @param pattern
     * @return String
     */
    public static String parseDate(Date date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).format(date);
        } catch (Exception e) {
            _log.error("Error while formatting the date " + date + ": " + e.getMessage());
        }
        return null;
    }

    public static String parseTime(Date time) {
        // Create a SimpleDateFormat object with the desired format.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        // Get the hour and minute from the Date object.
        int hour = time.getHours();
        int minute = time.getMinutes();

        // Create a string in the format 7:49 am.
        String formattedTime = String.format("%d:%02d %s", hour, minute, "am");

        return formattedTime;
    }
    /**
     * To get Date as input and provide date after 1 year as validity.
     * 
     * @param date
     * @return Date
     */
    public static Date generateOneYearValidity(Date date) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.YEAR, 1);
            return cal.getTime();
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

 

    public static String DateFormat(long currentTime) {
        Date date = new Date(currentTime);
        DateFormat formatter = new SimpleDateFormat("dd:MM:yy:HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        String dateFormatted = formatter.format(date);
        return dateFormatted;


    }

    public static DateFormat getDateFormatter(String pattern) {
        return new SimpleDateFormat(pattern);
    }
    private static Log _log = LogFactoryUtil.getLog(DateFormatterUtil.class);

 

}
