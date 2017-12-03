package by.imsha.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.*;

/**
 * @author Alena Misan
 */
public class ServiceUtils {

    public static String[] parseSortValue(String sort){
        if(StringUtils.isBlank(sort)){
            return null;
        }

        String[] values = new String[2];
        if(sort.length() > 1 && (sort.startsWith("+") || sort.startsWith("-"))){
            values[0]=sort.substring(1);
            values[1]=String.valueOf(sort.charAt(0));
        }else{
            values[0] = sort;
            values[1] = "+";
        }
        return values;

    }


    public static LocalDateTime timestampToLocalDate(long timestamp, ZoneId zoneId){
        return ZonedDateTime.ofInstant ( Instant.ofEpochSecond ( timestamp ) , zoneId ).toLocalDateTime();
    }


}
