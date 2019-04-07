package by.imsha.api.rest.serializers;

import by.imsha.domain.Parish;

import static org.hamcrest.MatcherAssert.*;

import static org.hamcrest.CoreMatchers.*;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Alena Misan
 */
public class ParishTest {


    @Test
    public void testNeedUpdate(){
        Parish parish = new Parish();
        parish.setUpdatePeriodInDays(14);
        LocalDateTime lastModifiedDate = LocalDateTime.parse("2018-06-23T00:27:16", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        parish.setLastModifiedDate(lastModifiedDate);
        assertThat(parish.isNeedUpdate(), equalTo(Boolean.TRUE));
    }

    @Test
    public void testNeedUpdateIfLastModifiedDateIsNull(){
        Parish parish = new Parish();
        parish.setUpdatePeriodInDays(14);
        parish.setLastModifiedDate(null);
        assertThat(parish.isNeedUpdate(), equalTo(Boolean.TRUE));
    }

}
