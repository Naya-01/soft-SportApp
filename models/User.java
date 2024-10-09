package models;

public class User {
    private String name;
    private String email;
    private String password;
    private String address;
    private Boolean isPremium;


    public User(String name, String email, String password, String address, Boolean isPremium) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.isPremium = isPremium;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public Boolean getIsPremium() {return isPremium;}

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIsPremium(Boolean isPremium) {this.isPremium = isPremium;}
}
