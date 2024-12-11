import controllers.AuthController;
import controllers.CLIController;
import controllers.ControllerInterface;

import controllers.MainController;
import java.util.Arrays;
import java.util.Scanner;
import views.AuthView;

public class Main{
    public static void main(String[] args) {

        CLIController cliController = new CLIController();

        AuthView authView = new AuthView(new AuthController());
        authView.setVisible(true);

        cliController.run();
    }

}