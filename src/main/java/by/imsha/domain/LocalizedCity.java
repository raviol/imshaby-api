package by.imsha.domain;

import java.util.Locale;

public class LocalizedCity extends LocalizedBaseInfo {

    public LocalizedCity(Locale locale, String originObjId, String name) {
        super(locale, originObjId);
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
