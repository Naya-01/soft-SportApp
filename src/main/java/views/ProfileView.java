package views;

import controllers.ExerciceController;

import javax.swing.*;
import java.awt.*;

public class ProfileView extends JFrame {

    private ExerciceController exerciceController;

    public ProfileView(ExerciceController exerciceController) {
        this.exerciceController = exerciceController;
        initComponents();
    }

    private void initComponents() {
        setTitle("User Profile");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(new JLabel("Nom: [Nom Utilisateur]"));
        panel.add(new JLabel("Email: [Email Utilisateur]"));

        // Ajouter le panel au JFrame
        add(panel);
    }
}
