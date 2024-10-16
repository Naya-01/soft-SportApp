package views;

import controllers.ExerciceController;
import models.domains.ExerciceDTO;
import models.exercices.Exercice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DashboardView extends JFrame {

    private ExerciceController exerciceController;

    public DashboardView(ExerciceController exerciceController) {
        this.exerciceController = exerciceController;
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
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfileView(exerciceController).setVisible(true);
            }
        });
        navBar.add(profileButton);

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
