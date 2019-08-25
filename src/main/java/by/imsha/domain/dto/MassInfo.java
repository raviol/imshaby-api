package by.imsha.domain.dto;

import by.imsha.rest.serializers.CustomLocalDateTimeSerializer;
import by.imsha.utils.ServiceUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String langCode;
    private MassParishInfo parish;
    private int duration;
    private String info;
    @JsonIgnore
    private LocalDate endDate;
    @JsonIgnore
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
                .append(duration, massInfo.duration)
                .append(langCode, massInfo.langCode)
                .append(parish, massInfo.parish)
                .append(info, massInfo.info)
                .append(endDate, massInfo.endDate)
                .append(startDate, massInfo.startDate)
                .append(lastModifiedDate, massInfo.lastModifiedDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(langCode)
                .append(parish)
                .append(duration)
                .append(info)
                .append(endDate)
                .append(startDate)
                .append(lastModifiedDate)
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
}
