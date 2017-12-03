package by.imsha.utils;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author Alena Misan
 */

public class ServiceUtilsTest {


    @Before
    public void setUp(){
    }

    @Test
    public void transformTimestampToDate_03122017(){
        LocalDateTime result =  ServiceUtils.timestampToLocalDate(1512399600, ZoneId.of("Europe/Minsk"));
        assertThat(result.getDayOfMonth(), equalTo(4));
    }

}
