package by.imsha.domain;

import java.util.Locale;
import java.util.Objects;

public class LocalizedCity extends LocalizedBaseInfo {

    public LocalizedCity(String lang, String originObjId, String name) {
        super(lang, originObjId);
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LocalizedCity that = (LocalizedCity) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName());
    }
}
