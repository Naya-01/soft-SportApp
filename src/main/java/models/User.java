package models;

import models.domains.ExerciceDTO;
import models.domains.UserDTO;
import models.domains.UserViewDTO;
import utils.JsonDBUtil;
import utils.UserSessionManager;

import java.util.List;
import java.util.UUID;

public class User {

    private static final String USER_FILE_PATH = "src/main/java/data/users.json";
    private UserDTO userDTO;

    public User() {}

    public User(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public UserDTO getUser(String name) {
        return JsonDBUtil.findObjectInJson(USER_FILE_PATH,"name", name, UserDTO.class);
    }

    public List<UserDTO> getAllUsers() {
        return JsonDBUtil.readFromJson(USER_FILE_PATH, UserDTO.class);
    }

    public void addUser(String name, String password, boolean isPremium) {
        UserDTO newUser = new UserDTO(name, password, isPremium);
        newUser.setId(UUID.randomUUID());
        JsonDBUtil.addObjectToJson(USER_FILE_PATH, newUser, UserDTO.class);
    }

    public boolean checkPassword(UserDTO user, String password) {
        return user.getPassword().equals(password);
    }

    public boolean isConnected() {
        return UserSessionManager.isUserConnected(userDTO);
    }

    public void connect(UserDTO user) {
        this.setUserDTO(user);
        UserSessionManager.addConnectedUser(userDTO);
        UserSessionManager.setUserConnected(userDTO);
    }

    public void disconnect() {
        UserSessionManager.removeConnectedUser(userDTO);
        UserSessionManager.setUserConnected(null);
        this.setUserDTO(null);
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public UserViewDTO parseToUserViewDTO(UserDTO userDTO) {
        UserViewDTO viewDTO = new UserViewDTO();
        viewDTO.setId(userDTO.getId());
        viewDTO.setName(userDTO.getName());
        viewDTO.setPremium(userDTO.getIsPremium());
        return viewDTO;
    }
}
