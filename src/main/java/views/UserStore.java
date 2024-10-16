package views;

import models.domains.UserViewDTO;

public class UserStore {
    private static UserStore instance = null;
    private static UserViewDTO currentUser = null;

    private UserStore() {}

    public static UserStore getInstance() {
        if (instance == null) {
            instance = new UserStore();
        }
        return instance;
    }

    public static UserViewDTO getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(UserViewDTO user) {
        currentUser = user;
    }
}
