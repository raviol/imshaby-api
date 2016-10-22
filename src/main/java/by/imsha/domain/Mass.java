package by.imsha.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ApiObjectField(description = "Language code of provided mass. Available codes are presented in ISO 639-2 Language Code List.", required = true)
    @NotNull
    private String langCode;

    @ApiObjectField(description = "Duration of mass in ms, default value = 3600 (1 hour)",  required = false)
    private long duration = 3600;

    @ApiObjectField(description = "Time of regular mass, that is defined throw time and days.", required = false)
    private LocalTime time;

    @ApiObjectField(description = "Array of days that is defined for regular mass. Days are presented via codes from 0 to 6:  Sunday = 0, Monday = 1 .. Saturday = 6", required = false)
    private short[] days;

    @ApiObjectField(description =  "Parish ID for mass", required = true)
    @NotNull
    private String parishId;

    @ApiObjectField(description = "Flag defines whether mass is deleted by merchant-user", required = false)
    private boolean deleted;

    @ApiObjectField(description = "Notes for mass created", required = false)
    private String notes;


    @ApiObjectField(description = "Start time for non regular mass, that occurs and is defined only once", required = false)
    private LocalDateTime  singleStartTime;


    @ApiObjectField(description = "End time for non regular mass, that occurs and is defined only once", required = false)
    private LocalDateTime singleEndTime;

    public Mass() {
    }

    public Mass(String langCode, long duration, String parishId, LocalTime time, LocalDateTime start, LocalDateTime end, short[] days) {
        this.langCode = langCode;
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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
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

    public short[] getDays() {
        return days;
    }

    public void setDays(short[] days) {
        this.days = days;
    }


}
