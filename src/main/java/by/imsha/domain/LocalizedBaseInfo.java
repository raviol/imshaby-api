package by.imsha.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Locale;

public class LocalizedBaseInfo {
    @JsonIgnore
    private Locale locale;
    @JsonIgnore
    private String originObjId;

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
}
