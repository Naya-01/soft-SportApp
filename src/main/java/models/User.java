package models;

import utils.JsonDBUtil;
import utils.UserSessionManager;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class User {

    private UUID id;
    private String name;
    private String password;
    private Boolean isPremium;

    private static final String USER_FILE_PATH = "src/main/java/data/users.json";

    public User() {}

    public User(String name, String password, Boolean isPremium) {
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

    public User getUser(String name) {
        return JsonDBUtil.findObjectInJson(USER_FILE_PATH,"name", name, User.class);
    }

    public void addUser(String name, String password, boolean isPremium) {
        User newUser = new User(name, password, isPremium);
        newUser.setId(UUID.randomUUID());
        JsonDBUtil.addObjectToJson(USER_FILE_PATH, newUser, User.class);
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public boolean isConnected() {
        return UserSessionManager.getInstance().isUserConnected(this);
    }

    public void connect() {
        UserSessionManager.getInstance().addConnectedUser(this);
    }

    public void disconnect() {
        UserSessionManager.getInstance().removeConnectedUser(this);
        UserSessionManager.currentUser = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
