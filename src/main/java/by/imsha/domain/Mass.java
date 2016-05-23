package by.imsha.domain;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

/**
 */
public class Mass {

    @Id
    private String id;

    @NotNull
    private String lang;

    private long timestamp;

    private long duration;

    @NotNull
    private String parishId;

    private boolean deleted;

    private String notes;


    private String time;

    private long start;

    private long end;

    private short[] days;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mass)) return false;

        Mass mass = (Mass) o;

        if (duration != mass.duration) return false;
        if (end != mass.end) return false;
        if (start != mass.start) return false;
        if (timestamp != mass.timestamp) return false;
        if (!Arrays.equals(days, mass.days)) return false;
        if (!lang.equals(mass.lang)) return false;
        if (!parishId.equals(mass.parishId)) return false;
        if (time != null ? !time.equals(mass.time) : mass.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lang.hashCode();
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + (int) (duration ^ (duration >>> 32));
        result = 31 * result + parishId.hashCode();
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (int) (start ^ (start >>> 32));
        result = 31 * result + (int) (end ^ (end >>> 32));
        result = 31 * result + (days != null ? Arrays.hashCode(days) : 0);
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public short[] getDays() {
        return days;
    }

    public void setDays(short[] days) {
        this.days = days;
    }


}
