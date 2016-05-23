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
    private String name;

    private String address;

    @NotNull
    private Coordinate gps;

    @NotNull
    private City city;

    private String phone;

    @NotNull
    private String supportPhone;

    @Email
    private String email;

    private String website;

    @NotNull
    @Size(min = 8, max = 16, message = "Please enter password with following rules: min letter count - 8, max - 16")
    private String password;

    @NotNull
    private String login;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parish)) return false;

        Parish parish = (Parish) o;
        if (!id.equals(parish.id)) return false;
        if (!city.equals(parish.city)) return false;
        if (!gps.equals(parish.gps)) return false;
        if (!login.equals(parish.login)) return false;
        if (!password.equals(parish.password)) return false;
        if (!supportPhone.equals(parish.supportPhone)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + gps.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + supportPhone.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + login.hashCode();
        return result;
    }
}
