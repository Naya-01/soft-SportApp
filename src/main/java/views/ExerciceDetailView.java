package views;

import controllers.ExerciceController;
import models.domains.MediaDTO;
import models.domains.ExerciceDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExerciceDetailView extends JFrame {

    private ExerciceController exerciceController;
    private ExerciceDTO exercice;

    public ExerciceDetailView(ExerciceController exerciceController, ExerciceDTO exercice) {
        this.exerciceController = exerciceController;
        this.exercice = exercice;
        initComponents();
    }

    private void initComponents() {
        setTitle("Exercice Details - " + exercice.getName());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel navBar = createNavBar();

        JPanel exerciceDetailsPanel = new JPanel();
        exerciceDetailsPanel.setLayout(new BoxLayout(exerciceDetailsPanel, BoxLayout.Y_AXIS));

        exerciceDetailsPanel.add(new JLabel("Nom : " + exercice.getName()));
        exerciceDetailsPanel.add(new JLabel("Type : " + exercice.getType()));
        exerciceDetailsPanel.add(new JLabel("Explication : " + exercice.getExplanation()));
        exerciceDetailsPanel.add(new JLabel("Difficulté : " + exercice.getDifficulty()));

        if (exercice.getMedias() != null && !exercice.getMedias().isEmpty()) {
            exerciceDetailsPanel.add(new JLabel("Médias :"));
            for (MediaDTO media : exercice.getMedias()) {
                exerciceDetailsPanel.add(new JLabel(media.getUrl()));
            }
        }

        mainPanel.add(navBar, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(exerciceDetailsPanel), BorderLayout.CENTER);

        add(mainPanel);
    }

    private JPanel createNavBar() {
        JPanel navBar = new JPanel();
        navBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton backButton = new JButton("Retour");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DashboardView().setVisible(true);
                dispose();
            }
        });

        navBar.add(backButton);
        return navBar;
    }
}
