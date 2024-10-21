package views;

import controllers.ExerciceController;
import controllers.UserController;
import models.domains.ExerciceDTO;

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

        JPanel navBar = createNavBar();

        JPanel exercicesPanel = new JPanel();
        exercicesPanel.setLayout(new BoxLayout(exercicesPanel, BoxLayout.Y_AXIS));

        List<ExerciceDTO> exercices = exerciceController.getAllExercices();
        for (ExerciceDTO exercice : exercices) {
            JButton exerciceButton = new JButton(exercice.getName());
            exerciceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ExerciceDetailView(exerciceController, exercice).setVisible(true);
                    dispose();
                }
            });
            exercicesPanel.add(exerciceButton);
        }

        mainPanel.add(navBar, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(exercicesPanel), BorderLayout.CENTER);

        add(mainPanel);
    }

    private JPanel createNavBar() {
        JPanel navBar = new JPanel();
        navBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton profileButton = new JButton("Voir Profil");
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfileView(exerciceController).setVisible(true);
            }
        });

        JButton premiumButton = new JButton("Become Premium");
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
        return navBar;
    }
}
