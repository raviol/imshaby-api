package by.imsha.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import java.util.Locale;
import java.util.Objects;

@ApiModel
public class LocalizedBaseInfo {
    @JsonIgnore
    private Locale locale;
    @JsonIgnore
    private String originObjId;

    public LocalizedBaseInfo() {
    }

    public LocalizedBaseInfo(Locale locale, String originObjId) {
        this.locale = locale;
        this.originObjId = originObjId;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getOriginObjId() {
        return originObjId;
    }

    public void setOriginObjId(String originObjId) {
        this.originObjId = originObjId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalizedBaseInfo that = (LocalizedBaseInfo) o;
        return Objects.equals(getLocale(), that.getLocale()) &&
                Objects.equals(getOriginObjId(), that.getOriginObjId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocale(), getOriginObjId());
    }
}
