package models.domains;

import models.User;

import java.util.Objects;
import java.util.UUID;

public class UserDTO {

    private UUID id;
    private String name;
    private String password;
    private Boolean isPremium;

    public UserDTO() {}

    public UserDTO(String name, String password, Boolean isPremium) {
        this.name = name;
        this.password = password;
        this.isPremium = isPremium;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() { return password; }

    public Boolean getIsPremium() {return isPremium;}

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) { this.password = password; }

    public void setIsPremium(Boolean isPremium) {this.isPremium = isPremium;}

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO user = (UserDTO) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
