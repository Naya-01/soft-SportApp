import controllers.AuthController;
import controllers.CommunityController;
import controllers.ExerciceController;
import models.domains.MediaDTO;
import models.domains.CustomExerciceDetailsDTO;
import models.domains.ExerciceDTO;
import models.domains.UserDTO;
import models.domains.UserViewDTO;
import models.enums.Difficulty;
import models.enums.ExerciceType;
import models.enums.MediaType;
import utils.Log;
import utils.UserSessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args){
        Log.config();
        Logger.getLogger("Log").log(Level.INFO, "Server started");

        AuthController authController = new AuthController();

        System.out.println("Connexion de newUser...");
        UserViewDTO user = authController.login("newUser", "newPassword");
        if (user != null) {
            System.out.println("Connexion réussie.");
        } else {
            System.out.println("Connexion échouée.");
        }

        testExercice();

        // testAuth();

        /*
        ControllerInterface controller = new ControllerImpl();
        while(true){
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
        }
        */
    }

    private static void testAuth() {

        AuthController authController = new AuthController();

        // Test de l'inscription
        System.out.println("Inscription de newUser...");
        boolean registrationSuccess = authController.register("newUser2", "newPassword5", false);
        if (registrationSuccess) {
            System.out.println("Inscription réussie.");
        } else {
            System.out.println("Inscription échouée.");
        }

        // Test de connexion
        System.out.println("Connexion de user1...");
        UserViewDTO loginSuccess = authController.login("user11", "password123");
        if (loginSuccess != null) {
            System.out.println("Connexion réussie.");
        } else {
            System.out.println("Connexion échouée.");
        }

        System.out.println("Connexion de newUser...");
        loginSuccess = authController.login("newUser", "newPassword");
        if (loginSuccess != null) {
            System.out.println("Connexion réussie.");
        } else {
            System.out.println("Connexion échouée.");
        }

        // double connexion
        System.out.println("Connexion de newUser 2...");
        loginSuccess = authController.login("newUser", "newPassword");
        if (loginSuccess != null) {
            System.out.println("Connexion réussie.");
        } else {
            System.out.println("Connexion échouée.");
        }

        // Vérifier les utilisateurs connectés
        System.out.println("Utilisateurs connectés :");
        List<UserDTO> connectedUsers = UserSessionManager.getConnectedUsers();
        for (UserDTO user : connectedUsers) {
            System.out.println(user.getName() + " (" + (user.getIsPremium() ? "Premium" : "Standard") + ")");
        }

        // Déconnexion
        authController.logout();
        System.out.println("Déconnexion de l'utilisateur.");

        // Affichage après déconnexion
        System.out.println("Utilisateurs connectés après déconnexion :");
        connectedUsers = UserSessionManager.getConnectedUsers();
        for (UserDTO user : connectedUsers) {
            System.out.println(user.getName() + " (" + (user.getIsPremium() ? "Premium" : "Standard") + ")");
        }
    }

    private static void testExercice() {
        ExerciceController exerciceController = new ExerciceController();
        CommunityController customExerciceController = new CommunityController();

        // Créer des médias pour les exercices
        List<MediaDTO> medias = new ArrayList<>();
        medias.add(new MediaDTO("http://example.com/video-cardio", MediaType.VIDEO));

        // Ajouter un exercice de type Cardio
        ExerciceDTO cardioExercice = exerciceController.addExercice(
                ExerciceType.CARDIO,
                "Running",
                "Run for 30 minutes",
                medias,
                Difficulty.INTERMEDIATE,
                true,
                30, 5000  // extraParams : durée et distance pour Cardio
        );
        if (cardioExercice != null) {
            System.out.println("Cardio Exercice ajouté: " + cardioExercice.getId());
        } else {
            System.out.println("Cardio Exercice existe déjà");
        }

        // Ajouter un exercice de type Strength
        ExerciceDTO strengthExercice = exerciceController.addExercice(
                ExerciceType.STRENGTH,
                "Push-ups",
                "Do 20 push-ups",
                medias,
                Difficulty.BEGINNER,
                true,
                20, 3  // extraParams : répétitions et séries pour Strength
        );
        if (strengthExercice != null) {
            System.out.println("Strength Exercice ajouté: " + strengthExercice.getId());
        } else {
            System.out.println("Strength Exercice existe déjà");
        }

        // Ajouter un exercice de type Flexibility
        ExerciceDTO flexibilityExercice = exerciceController.addExercice(
                ExerciceType.FLEXIBILITY,
                "Stretching",
                "Stretch for 15 minutes",
                medias,
                Difficulty.BEGINNER,
                true
        );
        if (flexibilityExercice != null) {
            System.out.println("Flexibility Exercice ajouté: " + flexibilityExercice.getId());
        } else {
            System.out.println("Flexibility Exercice existe déjà");
        }

        // Récupérer tous les exercices
        List<ExerciceDTO> allExercices = exerciceController.getAllExercices();
        System.out.println("Liste de tous les exercices:");
        allExercices.forEach(ex -> System.out.println(ex.getId() + ": " + ex.getName() + " (" + ex.getType() + ")"));

        // Récupérer tous les exercices
        List<CustomExerciceDetailsDTO> allCustomExercices = customExerciceController.getCustomExercicesWithDetails();
        System.out.println("Liste de tous les exercices custom:");

        // Récupérer les exercices de type Cardio et niveau INTERMEDIATE
        List<ExerciceDTO> cardioExercices = exerciceController.getExercicesByTypeAndDifficulty(Difficulty.INTERMEDIATE, ExerciceType.CARDIO);
        System.out.println("\nExercices de type Cardio et niveau INTERMEDIATE:");
        cardioExercices.forEach(ex -> System.out.println(ex.getId() + ": " + ex.getName()));

        // Récupérer les exercices de type Strength
        List<ExerciceDTO> strengthExercices = exerciceController.getExercicesByType(ExerciceType.STRENGTH);
        System.out.println("\nExercices de type Strength:");
        strengthExercices.forEach(ex -> System.out.println(ex.getId() + ": " + ex.getName()));

        // Supprimer l'exercice de type Flexibility
        boolean isDeleted = exerciceController.deleteExercice(flexibilityExercice.getId().toString());
        System.out.println("\nExercice de type Flexibility supprimé: " + isDeleted);

        // Vérifier la suppression
        List<ExerciceDTO> remainingExercices = exerciceController.getAllExercices();
        System.out.println("\nListe des exercices après suppression:");
        remainingExercices.forEach(ex -> System.out.println(ex.getId() + ": " + ex.getName()));

    }
}
