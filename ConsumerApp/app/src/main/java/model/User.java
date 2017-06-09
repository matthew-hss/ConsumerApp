package model;

/**
 * Created by matthew on 08-06-17.
 */

public class User {
    private String rut = null;
    private String firstName = null;
    private String lastName = null;
    private String gender = null;
    private String commune = null;
    private String email = null;
    private Long phone = null;
    private Long ekopesos = null;
    private Long co2 = null;
    private Integer maxLimit = null;
    private boolean active = false;

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getEkopesos() {
        return ekopesos;
    }

    public void setEkopesos(Long ekopesos) {
        this.ekopesos = ekopesos;
    }

    public Long getCo2() {
        return co2;
    }

    public void setCo2(Long co2) {
        this.co2 = co2;
    }

    public Integer getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(Integer maxLimit) {
        this.maxLimit = maxLimit;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
