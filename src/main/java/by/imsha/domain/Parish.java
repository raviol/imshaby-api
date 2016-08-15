package by.imsha.domain;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * Represent Parish class
 */
public class Parish {

    @Id
    private String id;

    @NotNull
    private String userId;

    @NotNull
    private String name;

    private String address;

    @NotNull
    private Coordinate gps;

    public Parish(){}

    public Parish(String userId, String name, Coordinate gps, City city, String supportPhone, String email) {
        this.userId = userId;
        this.name = name;
        this.gps = gps;
        this.city = city;
        this.supportPhone = supportPhone;
        this.email = email;
    }

    @NotNull
    private City city;

    private String phone;

    @NotNull
    private String supportPhone;

    @Email
    @NotNull
    private String email;

    private String website;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parish)) return false;

        Parish parish = (Parish) o;

        if (!city.equals(parish.city)) return false;
        if (!email.equals(parish.email)) return false;
        if (!gps.equals(parish.gps)) return false;
        if (!id.equals(parish.id)) return false;
        if (!name.equals(parish.name)) return false;
        if (!userId.equals(parish.userId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + gps.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Coordinate getGps() {
        return gps;
    }

    public void setGps(Coordinate gps) {
        this.gps = gps;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSupportPhone() {
        return supportPhone;
    }

    public void setSupportPhone(String supportPhone) {
        this.supportPhone = supportPhone;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}
