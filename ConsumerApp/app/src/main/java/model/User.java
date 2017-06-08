package model;

/**
 * Created by matthew on 08-06-17.
 */

public class User {
    private Long id = null;
    private Integer rut = null;
    private String password = "temporal";
    private String username = null;
    private String firstName = null;
    private String lastName = null;
    //private Gender gender = Gender.FEMALE;
    private Commune commune = null;
    private String email = null;
    private Long phone = null;
    private Long ekopesos = null;
    private Long co2 = null;
    private Integer maxLimit = null;
    private boolean completeProfile = false;
    private boolean active = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRut() {
        return rut;
    }

    public void setRut(Integer rut) {
        this.rut = rut;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
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

    public boolean isCompleteProfile() {
        return completeProfile;
    }

    public void setCompleteProfile(boolean completeProfile) {
        this.completeProfile = completeProfile;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
