package by.imsha.api.rest.serializers;

import by.imsha.utils.ServiceUtils;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Alena Misan
 */

public class CustomLocalDateTimeSerializerTest {

    private final String pattern = "dd-MM-yyyy HH:mm:ss";
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
    private CustomLocalDateTimeSerializer customTimeSerializer;

    @Before
    public void setUp(){
        customTimeSerializer = new CustomLocalDateTimeSerializer();
    }

    @Test
    public void shouldTransformLondonTime_212505_To_MinskTime_002505(){
        String timeString = "25-01-2018 00:25:05";
        ZonedDateTime now = LocalDateTime.parse(timeString, dateTimeFormatter).atZone(ZoneId.of("Europe/Minsk"));
        ZonedDateTime nowLondon = now.withZoneSameInstant(ZoneId.of("Europe/London"));
        System.out.println("nowLondon = " + nowLondon);
        String minskTimeString = customTimeSerializer.timeStringInTimezone(nowLondon, pattern, ZoneId.of("Europe/Minsk"));
        assertThat(minskTimeString, is(equalTo(timeString)));
    }

}
