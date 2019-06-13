package by.imsha.domain;

import java.util.Locale;

public class LocalizedParish extends LocalizedBaseInfo {
    private String name;
    private String address;
    public LocalizedParish(Locale locale, String originObjId) {
        super(locale, originObjId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
