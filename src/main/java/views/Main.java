package views;

import controllers.AuthController;
import controllers.ControllerImpl;
import controllers.ControllerInterface;

import java.util.Scanner;

public class Main{
    public static void main(String[] args){


        AuthView authView = new AuthView(new AuthController());
        authView.setVisible(true);



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

        }
    }