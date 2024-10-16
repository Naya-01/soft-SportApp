package utils;

import models.User;
import models.domains.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserSessionManager {
    public static UserDTO currentUser = null;
    public static UserSessionManager instance = null;
    private static List<UserDTO> connectedUsers = new ArrayList<>();

    private UserSessionManager() {
    }

    public static UserSessionManager getInstance() {
        if (instance == null) {
            instance = new UserSessionManager();
        }
        return instance;
    }

    public static void addConnectedUser(UserDTO user) {
        if (!connectedUsers.contains(user)) {
            connectedUsers.add(user);
        }
    }

    public static void removeConnectedUser(UserDTO user) {
        connectedUsers.remove(user);
    }

    public static List<UserDTO> getConnectedUsers() {
        return new ArrayList<>(connectedUsers);
    }

    public static boolean isUserConnected(UserDTO user) {
        return connectedUsers.contains(user);
    }

    public static void setUserConnected(UserDTO user) {
        currentUser = user;
    }

    public static void clearSessions() {
        connectedUsers.clear();
    }
}