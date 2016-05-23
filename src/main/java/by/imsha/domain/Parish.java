package by.imsha.domain;

import org.springframework.data.annotation.Id;

/**
 *
 * Represent Parish class
 */
public class Parish {

    @Id
    private String id;

    private String name;

    private String address;

    private Coordinate gps;

    private City city;

    private String phone;

    private String supportPhone;

    private String email;

    private String website;

    private String password;

    private String login;


    // TODO fix equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parish)) return false;

        Parish parish = (Parish) o;

        if (address != null ? !address.equals(parish.address) : parish.address != null) return false;
        if (city != null ? !city.equals(parish.city) : parish.city != null) return false;
        if (email != null ? !email.equals(parish.email) : parish.email != null) return false;
        if (gps != null ? !gps.equals(parish.gps) : parish.gps != null) return false;
        if (id != null ? !id.equals(parish.id) : parish.id != null) return false;
        if (login != null ? !login.equals(parish.login) : parish.login != null) return false;
        if (name != null ? !name.equals(parish.name) : parish.name != null) return false;
        if (password != null ? !password.equals(parish.password) : parish.password != null) return false;
        if (phone != null ? !phone.equals(parish.phone) : parish.phone != null) return false;
        if (supportPhone != null ? !supportPhone.equals(parish.supportPhone) : parish.supportPhone != null)
            return false;
        if (website != null ? !website.equals(parish.website) : parish.website != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (gps != null ? gps.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (supportPhone != null ? supportPhone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
