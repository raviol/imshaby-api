package by.imsha.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * @author Alena Misan
 */
public class ServiceUtils {

    private static String dateFormat = "dd-MM-yyyy";
    private static String timeFormat = "dd-MM-yyyy HH:mm";


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

    public static long dateToUTCTimestamp(String day) throws DateTimeParseException{
        LocalDate date = null;
        if(day != null){
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
                date = LocalDate.parse(day, formatter);
            }catch (DateTimeParseException ex){
                throw new DateTimeParseException(String.format("Date format is incorrect. Date - %s,format - %s ", day, dateFormat), ex.getParsedString(), ex.getErrorIndex());
            }
        }

        return date.atStartOfDay().toEpochSecond(ZoneOffset.UTC);
    }
  
    public static ZonedDateTime localDateTimeToZoneDateTime(LocalDateTime localDateTime, ZoneId fromZone, ZoneId toZone) {
        ZonedDateTime date = ZonedDateTime.of(localDateTime, fromZone);
        return date.withZoneSameInstant(toZone);
    }

    public static long dateTimeToUTCTimestamp(String day) throws DateTimeParseException{
        LocalDateTime date = null;
        if(day != null){
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);
                date = LocalDateTime.parse(day, formatter);
            }catch (DateTimeParseException ex){
                throw new DateTimeParseException(String.format("Date time format is incorrect. Date time - %s,format - %s ", day, timeFormat), ex.getParsedString(), ex.getErrorIndex());
            }
        }

        return date.toEpochSecond(ZoneOffset.UTC);
    }

    /**
     *
     * */
    public static boolean needUpdateFromNow(LocalDateTime pLastModifiedDate, int pUpdatePeriodInDays) {
        LocalDateTime now = LocalDateTime.now();
        boolean result;
        ZonedDateTime nowTime = ServiceUtils.localDateTimeToZoneDateTime(now, ZoneId.systemDefault(), ZoneId.of("Europe/Minsk"));
        if(pLastModifiedDate == null){
            result = true;
        }else{
            result = Math.abs(ChronoUnit.DAYS.between(nowTime.toLocalDate(), pLastModifiedDate.toLocalDate().minusDays(1))) > pUpdatePeriodInDays;
        }
        return result;
    }


}
