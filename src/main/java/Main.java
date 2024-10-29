import controllers.AuthController;
import controllers.ControllerInterface;

import controllers.MainController;
import java.io.File;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Log;
import views.AuthView;

public class Main{
    public static void main(String[] args) {

        Log.config();
        Logger.getLogger("Log").log(Level.INFO, "Server started");


        ControllerInterface controller = new MainController();

        AuthView authView = new AuthView(new AuthController());
        authView.setVisible(true);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("What do you want to do ? (deactivations, activations, enableUIView, disableUIView, getStateAsLog) ");
            String feature = sc.nextLine();

            switch (feature) {
                case "deactivations":
                    System.out.println("Enter a list of features you want to deactivate separated by whitespace: ");
                    String deactivations = sc.nextLine();
                    controller.activate(deactivations.split(" "), null);
                    break;
                case "activations":
                    System.out.println("Enter a list of features you want to activate separated by whitespace: ");
                    String activations = sc.nextLine();
                    controller.activate(null, activations.split(" "));
                    break;
                case "enableUIView":
                    controller.enableUIView();
                    break;
                case "disableUIView":
                    controller.disableUIView();
                    break;
                case "getStateAsLog":
                    System.out.println(Arrays.toString(controller.getStateAsLog()));
                    break;
                default:
                    System.out.println("Invalid entry");
            }
        }
    }

}