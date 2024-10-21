package views;

import controllers.ExerciceController;
import controllers.UserController;
import models.domains.ExerciceDTO;
import models.exercices.Exercice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DashboardView extends JFrame {

    private ExerciceController exerciceController;
    private UserController userController;

    public DashboardView(ExerciceController exerciceController) {
        this.exerciceController = exerciceController;
        this.userController = new UserController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Dashboard - Exercices");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel navBar = new JPanel();
        navBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton profileButton = new JButton("Voir Profil");
        JButton premiumButton = new JButton("Become Premium");
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfileView(exerciceController).setVisible(true);
            }
        });

        premiumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userController.upgradeAccount();
                premiumButton.setVisible(false);
            }
        });

        premiumButton.setVisible(!UserStore.getCurrentUser().getPremium());
        navBar.add(profileButton);
        navBar.add(premiumButton);

        JPanel exercicesPanel = new JPanel();
        exercicesPanel.setLayout(new BoxLayout(exercicesPanel, BoxLayout.Y_AXIS));

        List<ExerciceDTO> exercices = exerciceController.getAllExercices();
        for (ExerciceDTO exercice : exercices) {
            JLabel exerciceLabel = new JLabel(exercice.getName());
            exercicesPanel.add(exerciceLabel);
        }

        mainPanel.add(navBar, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(exercicesPanel), BorderLayout.CENTER);

        add(mainPanel);
    }
}
