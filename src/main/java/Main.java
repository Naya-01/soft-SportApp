import controllers.AuthController;
import controllers.ControllerImpl;
import controllers.ControllerInterface;
import models.User;
import utils.Log;
import utils.UserSessionManager;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Log.config();
        Logger.getLogger("Log").log(Level.INFO, "Server started");
        ControllerInterface controller = new ControllerImpl();

        AuthController authController = new AuthController();

        // Test de l'inscription
        System.out.println("Inscription de newUser...");
        boolean registrationSuccess = authController.register("newUser", "newPassword", "newuser@example.com", "789 Third St", false);
        if (registrationSuccess) {
            System.out.println("Inscription réussie.");
        } else {
            System.out.println("Inscription échouée.");
        }

        // Test de connexion
        System.out.println("Connexion de user1...");
        boolean loginSuccess = authController.login("user1", "password123");
        if (loginSuccess) {
            System.out.println("Connexion réussie.");
        } else {
            System.out.println("Connexion échouée.");
        }

        System.out.println("Connexion de newUser...");
        loginSuccess = authController.login("newUser", "newPassword");
        if (loginSuccess) {
            System.out.println("Connexion réussie.");
        } else {
            System.out.println("Connexion échouée.");
        }

        // Vérifier les utilisateurs connectés
        System.out.println("Utilisateurs connectés :");
        List<User> connectedUsers = UserSessionManager.getInstance().getConnectedUsers();
        for (User user : connectedUsers) {
            System.out.println(user.getName() + " (" + (user.getIsPremium() ? "Premium" : "Standard") + ")");
        }

        // Déconnexion
        authController.logout();
        System.out.println("Déconnexion de l'utilisateur.");

        // Affichage après déconnexion
        System.out.println("Utilisateurs connectés après déconnexion :");
        connectedUsers = UserSessionManager.getInstance().getConnectedUsers();
        for (User user : connectedUsers) {
            System.out.println(user.getName() + " (" + (user.getIsPremium() ? "Premium" : "Standard") + ")");
        }

        /*while(true){
            Scanner sc = new Scanner(System.in);
            System.out.println("What do you want to do ? (deactivations, activations, enableUIView, disableUIView, getStateAsLog) ");
            String feature = sc.nextLine();
            switch (feature){
                case "deactivations":
                    System.out.println("Enter a list of features you want to deactivate separated by whitespace : ");
                    String deactivations = sc.nextLine();
                    controller.activate(deactivations.split(" "),null);
                    break;
                case "activations":
                    System.out.println("Enter a list of features you want to activate separated by whitespace : ");
                    String activations = sc.nextLine();
                    controller.activate(null, activations.split(" "));
                    break;
                case "enableUIView":
                    System.out.println("UI View enabled");
                    break;
                case "disableUIView":
                    System.out.println("UI View disabled");
                    break;
                case "getStateAsLog":
                    System.out.println("System's state in the form of logs");
                    break;
                default:
                    System.out.println("Invalid entry");
            }
        }*/
    }
}
