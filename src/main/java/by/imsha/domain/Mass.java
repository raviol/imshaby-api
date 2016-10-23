package by.imsha.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

/**
 */
@ApiObject(show = true, name = "Mass", description = "Mass object json structure.")
public class Mass {

    @Id
    private String id;

    @ApiObjectField(description = "City ID.", required = true)
    @NotNull
    private String cityId;


    @ApiObjectField(description = "Language code of provided mass. Available codes are presented in ISO 639-2 Language Code List.", required = true)
    @NotNull
    private String langCode;

    @ApiObjectField(description = "Duration of mass in ms, default value = 3600 (1 hour)",  required = false)
    private long duration = 3600;

    @ApiObjectField(description = "Time of regular mass, that is defined throw time and days.", required = false)
//    @JsonFormat(pattern = "KK:mm")
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private String time;

    @ApiObjectField(description = "Array of days that is defined for regular mass. Days are presented via codes from 1 to 7:  Monday = 1 .. Saturday = 6, Sunday = 7", required = false)
    private int[] days;

    @ApiObjectField(description =  "Parish ID for mass", required = true)
    @NotNull
    private String parishId;

    @ApiObjectField(description = "Flag defines whether mass is deleted by merchant-user", required = false)
    private boolean deleted;

    @ApiObjectField(description = "Notes for mass created", required = false)
    private String notes;


    @ApiObjectField(description = "Start time for non regular mass, that occurs and is defined only once", required = false)
//    @JsonFormat(pattern = "dd-MM-yyyy hh:mm")
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime  singleStartTime;


    @ApiObjectField(description = "End time for non regular mass, that occurs and is defined only once", required = false)
//    @JsonFormat(pattern = "dd-MM-yyyy hh:mm")
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime singleEndTime;

    public Mass() {
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }



    public Mass(String langCode,String cityId, long duration, String parishId, String time, LocalDateTime start, LocalDateTime end, int[] days) {
        this.langCode = langCode;
        this.cityId = cityId;
        this.duration = duration;
        this.parishId = parishId;
        this.time = time;
        this.singleStartTime = start;
        this.singleEndTime = end;
        this.days = days;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mass)) return false;

        Mass mass = (Mass) o;

        if (duration != mass.duration) return false;
        if (singleEndTime != mass.singleEndTime) return false;
        if (singleStartTime != mass.singleStartTime) return false;
        if (!Arrays.equals(days, mass.days)) return false;
        if (!langCode.equals(mass.langCode)) return false;
        if (!parishId.equals(mass.parishId)) return false;
        if (time != null ? !time.equals(mass.time) : mass.time != null) return false;

        return true;
    }


    @Override
    public int hashCode() {
        int result = langCode != null ? langCode.hashCode() : 0;
        result = 31 * result + (int) (duration ^ (duration >>> 32));
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (days != null ? Arrays.hashCode(days) : 0);
        result = 31 * result + (parishId != null ? parishId.hashCode() : 0);
        result = 31 * result + (singleStartTime != null ? singleStartTime.hashCode() : 0);
        result = 31 * result + (singleEndTime != null ? singleEndTime.hashCode() : 0);
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }


    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getParishId() {
        return parishId;
    }

    public void setParishId(String parishId) {
        this.parishId = parishId;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LocalDateTime getSingleStartTime() {
        return singleStartTime;
    }

    public void setSingleStartTime(LocalDateTime singleStartTime) {
        this.singleStartTime = singleStartTime;
    }

    public LocalDateTime getSingleEndTime() {
        return singleEndTime;
    }

    public void setSingleEndTime(LocalDateTime singleEndTime) {
        this.singleEndTime = singleEndTime;
    }

    public int[] getDays() {
        return days;
    }

    public void setDays(int[] days) {
        this.days = days;
    }


}
