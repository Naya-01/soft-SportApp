package models.domains;

import java.util.UUID;

public class UserViewDTO {

    private UUID id;
    private String name;
    private Boolean isPremium;

    public UserViewDTO() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPremium() {
        return isPremium;
    }

    public void setPremium(Boolean premium) {
        isPremium = premium;
    }

    @Override
    public String toString() {
        return "UserViewDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isPremium=" + isPremium +
                '}';
    }
}
