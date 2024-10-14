package models;

import java.util.UUID;

public class User {

    private UUID id;
    private String name;
    private String password;
    private String email;
    private String address;
    private Boolean isPremium;

    public User() {}

    public User(String name, String password, String email, String address, Boolean isPremium) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
        this.isPremium = isPremium;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() { return password; }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Boolean getIsPremium() {return isPremium;}

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) { this.password = password; }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIsPremium(Boolean isPremium) {this.isPremium = isPremium;}

    public void setId(UUID id) {
        this.id = id;
    }
}
