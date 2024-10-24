package views;

import controllers.AuthController;
import controllers.ControllerInterface;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main{
    private static final String CONTROLLERS_PACKAGE = "controllers";
    public static void main(String[] args) {


        AuthView authView = new AuthView(new AuthController());
        authView.setVisible(true);


        Map<String, ControllerInterface> controllers = new HashMap<>();
        fillControllersMap(controllers);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("What do you want to do ? (deactivations, activations, enableUIView, disableUIView, getStateAsLog) ");
            String feature = sc.nextLine();

            switch (feature) {
                case "deactivations":
                    System.out.println("Enter a list of features you want to deactivate separated by whitespace: ");
                    String deactivations = sc.nextLine();
                    ControllerInterface controller = controllers.get("exercicecontroller");
                    controller.activate(deactivations.split(" "), null);
                    break;
                case "activations":
                    System.out.println("Enter a list of features you want to activate separated by whitespace: ");
                    String activations = sc.nextLine();
                    controller = controllers.get("exercicecontroller");
                    controller.activate(null, activations.split(" "));
                    break;
                case "enableUIView":
                    handleUIViewAction(controllers, sc, true);
                    break;
                case "disableUIView":
                    handleUIViewAction(controllers, sc, false);
                    break;
                case "getStateAsLog":
                    controller = controllers.get("exercicecontroller");
                    String[] stateLogs = controller.getStateAsLog();
                    for (String log : stateLogs) {
                        System.out.println(log);
                    }
                    break;
                default:
                    System.out.println("Invalid entry");
            }
        }
    }

    private static void handleUIViewAction(Map<String, ControllerInterface> controllers, Scanner sc, boolean enable) {
        Map<String, String> simplifiedControllers = getSimplifiedControllerNames(controllers);
        System.out.println(simplifiedControllers.keySet());

        String action = enable ? "enable" : "disable";
        System.out.println("Choose controller to " + action + " UI View: ");
        String controllerChoice = sc.nextLine().toLowerCase();

        String originalKey = simplifiedControllers.get(controllerChoice);
        ControllerInterface controller = controllers.get(originalKey);

        if (controller == null) {
            System.out.println("Invalid controller selected.");
        } else {
            boolean result = enable ? controller.enableUIView() : controller.disableUIView();
            if (result) {
                System.out.println("UI View " + action + "d for " + controllerChoice);
            } else {
                System.out.println("Failed to " + action + " UI View for " + controllerChoice);
            }
        }
    }

    private static Map<String, String> getSimplifiedControllerNames(Map<String, ControllerInterface> controllers) {
        Map<String, String> simplifiedControllers = new HashMap<>();
        for (String controllerKey : controllers.keySet()) {
            String simplifiedName = controllerKey.replaceAll("(?i)controller", "").toLowerCase();
            simplifiedControllers.put(simplifiedName, controllerKey);
        }
        return simplifiedControllers;
    }


    private static void fillControllersMap(Map<String, ControllerInterface> controllers) {
        try {
            String path = Main.class.getClassLoader().getResource(CONTROLLERS_PACKAGE.replace(".", "/")).getPath();
            File directory = new File(path);

            for (File file : directory.listFiles()) {
                if (file.getName().endsWith(".class")) {
                    String className = file.getName().replace(".class", "");
                    String fullClassName = CONTROLLERS_PACKAGE + "." + className;

                    Class<?> clazz = Class.forName(fullClassName);

                    if (!Modifier.isAbstract(clazz.getModifiers()) && ControllerInterface.class.isAssignableFrom(clazz)) {
                        try {
                            if (clazz.getDeclaredConstructor() != null) {
                                ControllerInterface controller = (ControllerInterface) clazz.getDeclaredConstructor().newInstance();
                                controllers.put(className.toLowerCase(), controller);
                            }
                        } catch (NoSuchMethodException e) {
                            System.out.println("No default constructor found for class: " + className);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}