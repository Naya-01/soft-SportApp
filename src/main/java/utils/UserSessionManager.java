package utils;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UserSessionManager {
    public static User currentUser = null;
    public static UserSessionManager instance = null;
    private List<User> connectedUsers;

    private UserSessionManager() {
        connectedUsers = new ArrayList<>();
    }

    public static UserSessionManager getInstance() {
        if (instance == null) {
            instance = new UserSessionManager();
        }
        return instance;
    }

    public void addConnectedUser(User user) {
        if (!connectedUsers.contains(user)) {
            connectedUsers.add(user);
        }
    }

    public void removeConnectedUser(User user) {
        connectedUsers.remove(user);
    }

    public List<User> getConnectedUsers() {
        return new ArrayList<>(connectedUsers);
    }

    public boolean isUserConnected(User user) {
        return connectedUsers.contains(user);
    }

    public static User setUserConnected(User user) {
        currentUser = user;
        return currentUser;
    }

    public void clearSessions() {
        connectedUsers.clear();
    }
}