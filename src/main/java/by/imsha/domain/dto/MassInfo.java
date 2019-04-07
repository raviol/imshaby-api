package by.imsha.domain.dto;

import by.imsha.api.rest.serializers.CustomLocalDateTimeSerializer;
import by.imsha.utils.ServiceUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Alena Misan
 */
public class MassInfo implements Serializable{
    private String langCode;
    private MassParishInfo parish;
    private int duration;
    private String info;

    @JsonSerialize(using= CustomLocalDateTimeSerializer.class)
    private LocalDateTime lastModifiedDate;

    public boolean isNeedUpdate() {
        return ServiceUtils.needUpdateFromNow(lastModifiedDate, parish.getUpdatePeriodInDays()) && parish.isNeedUpdate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MassInfo)) return false;

        MassInfo massInfo = (MassInfo) o;

        if (duration != massInfo.duration) return false;
        if (!langCode.equals(massInfo.langCode)) return false;
        if (!parish.equals(massInfo.parish)) return false;
        return info != null ? info.equals(massInfo.info) : massInfo.info == null;
    }

    @Override
    public int hashCode() {
        int result = langCode.hashCode();
        result = 31 * result + parish.hashCode();
        result = 31 * result + duration;
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MassInfo{" +
                "langCode='" + langCode + '\'' +
                ", parish=" + parish +
                ", duration=" + duration +
                ", info='" + info + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
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
