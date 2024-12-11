package controllers;

import java.util.Arrays;
import java.util.Scanner;

public class CLIController extends MainController{

    public CLIController() {
        super();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("What do you want to do ? (deactivations, activations, enableUIView, disableUIView, getStateAsLog) ");
            String feature = sc.nextLine();

            switch (feature) {
                case "deactivations":
                    System.out.println("Enter a list of features you want to deactivate separated by whitespace: ");
                    String deactivations = sc.nextLine();
                    this.activate(deactivations.split(" "), null);
                    break;
                case "activations":
                    System.out.println("Enter a list of features you want to activate separated by whitespace: ");
                    String activations = sc.nextLine();
                    this.activate(null, activations.split(" "));
                    break;
                case "enableUIView":
                    this.enableUIView();
                    break;
                case "disableUIView":
                    this.disableUIView();
                    break;
                case "getStateAsLog":
                    System.out.println(Arrays.toString(this.getStateAsLog()));
                    break;
                default:
                    System.out.println("Invalid entry");
            }
        }
    }
}
