package by.imsha.domain.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author Alena Misan
 */
public class LocalizedCityInfo {
    @NotNull
    @NotEmpty
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
        if (!(o instanceof LocalizedCityInfo)) return false;

        LocalizedCityInfo cityInfo = (LocalizedCityInfo) o;

        return name != null ? name.equals(cityInfo.name) : cityInfo.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
