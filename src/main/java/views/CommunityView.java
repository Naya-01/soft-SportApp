package views;

import controllers.CommunityController;
import models.domains.CustomExerciceDetailsDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CommunityView extends JFrame {

    private CommunityController customExerciceController;

    public CommunityView() {
        this.customExerciceController = new CommunityController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Community - Custom Exercises");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel navBar = createNavBar();

        JPanel exercicesPanel = new JPanel();
        exercicesPanel.setLayout(new BoxLayout(exercicesPanel, BoxLayout.Y_AXIS));

        List<CustomExerciceDetailsDTO> customExercices = customExerciceController.getCustomExercicesWithDetails();
        for (CustomExerciceDetailsDTO customExercice : customExercices) {
            JLabel exerciceLabel = new JLabel(customExercice.getExercice().getName() + " by " + customExercice.getUser().getName());
            exercicesPanel.add(exerciceLabel);
        }

        mainPanel.add(navBar, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(exercicesPanel), BorderLayout.CENTER);

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
