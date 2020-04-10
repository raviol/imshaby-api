package by.imsha.domain.dto;

import by.imsha.rest.serializers.CustomLocalDateTimeSerializer;
import by.imsha.rest.serializers.LocalDateSerializer;
import by.imsha.utils.ServiceUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Alena Misan
 */
public class MassInfo implements Serializable{
    private String id;
    private String langCode;
    private MassParishInfo parish;
    private int duration;
    private String info;
    private int[] days;
    private boolean online;
    //@JsonIgnore
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate endDate;
    //@JsonIgnore
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;


    @JsonSerialize(using= CustomLocalDateTimeSerializer.class)
    private LocalDateTime lastModifiedDate;

    public boolean isNeedUpdate() {
        return ServiceUtils.needUpdateFromNow(lastModifiedDate, parish.getUpdatePeriodInDays()) && parish.isNeedUpdate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MassInfo massInfo = (MassInfo) o;

        return new EqualsBuilder()
                .append(getDuration(), massInfo.getDuration())
                .append(getId(), massInfo.getId())
                .append(getLangCode(), massInfo.getLangCode())
                .append(getParish(), massInfo.getParish())
                .append(getInfo(), massInfo.getInfo())
                .append(getDays(), massInfo.getDays())
                .append(getEndDate(), massInfo.getEndDate())
                .append(getStartDate(), massInfo.getStartDate())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getLangCode())
                .append(getParish())
                .append(getDuration())
                .append(getInfo())
                .append(getDays())
                .append(getEndDate())
                .append(getStartDate())
                .toHashCode();
    }

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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("days", days)
                .append("langCode", langCode)
                .append("parish", parish)
                .append("duration", duration)
                .append("info", info)
                .append("endDate", endDate)
                .append("startDate", startDate)
                .append("lastModifiedDate", lastModifiedDate)
                .toString();
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public MassParishInfo getParish() {
        return parish;
    }

    public void setParish(MassParishInfo parish) {
        this.parish = parish;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int[] getDays() {
        return days;
    }

    public void setDays(int[] days) {
        this.days = days;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
