package utils;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UserSessionManager {
    private static UserSessionManager instance = null;
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

    public void addUser(User user) {
        if (!connectedUsers.contains(user)) {
            connectedUsers.add(user);
        }
    }

    public void removeUser(User user) {
        connectedUsers.remove(user);
    }

    public List<User> getConnectedUsers() {
        return new ArrayList<>(connectedUsers);
    }

    public boolean isUserConnected(User user) {
        return connectedUsers.contains(user);
    }

    public void clearSessions() {
        connectedUsers.clear();
    }
}