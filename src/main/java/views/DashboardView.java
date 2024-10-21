package views;

import controllers.CustomExerciceController;
import controllers.ExerciceController;
import controllers.UserController;
import models.domains.ExerciceDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import views.utils.UserStore;

public class DashboardView extends JFrame {

    private ExerciceController exerciceController;
    private UserController userController;
    private CustomExerciceController customExerciceController;

    public DashboardView() {
        this.exerciceController = new ExerciceController();
        this.userController = new UserController();
        this.customExerciceController = new CustomExerciceController();
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

        List<ExerciceDTO> exercices = exerciceController.getAllNoCustomExercices();
        for (ExerciceDTO exercice : exercices) {
            JButton exerciceButton = new JButton(exercice.getName());
            exerciceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ExerciceDetailView(exercice).setVisible(true);
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

        JButton communityButton = new JButton("Community");
        communityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CommunityView().setVisible(true);
                dispose();
            }
        });

        navBar.add(profileButton);
        navBar.add(premiumButton);
        navBar.add(communityButton);
        return navBar;
    }
}
