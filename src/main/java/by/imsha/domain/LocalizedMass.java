package by.imsha.domain;

import java.util.Objects;

public class LocalizedMass {

    private String notes;

    public LocalizedMass() {
    }

    public LocalizedMass(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LocalizedMass that = (LocalizedMass) o;
        return Objects.equals(getNotes(), that.getNotes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNotes());
    }
}
