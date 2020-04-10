package by.imsha.domain.dto;

import by.imsha.rest.serializers.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Alena Misan
 */

public class UpdateMassInfo implements Serializable{
    private String langCode;
    private int duration;
    private String notes;
    private int[] days;
    private boolean online;
    //@JsonIgnore
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate endDate;
    //@JsonIgnore
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;

    private Long singleStartTimestamp;

    @Pattern(regexp = "^[0-2][0-9]:[0-5][0-9]$")
    private String time;

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int[] getDays() {
        return days;
    }

    public void setDays(int[] days) {
        this.days = days;
    }

    public Long getSingleStartTimestamp() {
        return singleStartTimestamp;
    }

    public void setSingleStartTimestamp(Long singleStartTimestamp) {
        this.singleStartTimestamp = singleStartTimestamp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
