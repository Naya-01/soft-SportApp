package views;

import controllers.CommunityController;
import models.domains.CustomExerciceDetailsDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.logging.Logger;

import utils.Log;
import views.components.ExercicesPanel;
import views.utils.BaseView;

public class CommunityView extends BaseView {

    private CommunityController communityController;

    private ExercicesPanel exercicesPanel;

    private JPanel mainPanel;
    private JPanel navBar;

    private JButton backButton;

    public CommunityView() {
        this.communityController = new CommunityController();
        this.logger = Log.getLogger();
        logger.info("Community view rendered");
        initComponents();
    }

    private void initComponents() {
        setTitle("Community - Custom Exercises");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());
        navBar = createNavBar();

        mainPanel.add(navBar, BorderLayout.NORTH);

        List<CustomExerciceDetailsDTO> customExercices = communityController.getCustomExercicesWithDetails();
        exercicesPanel = new ExercicesPanel(this::openExerciceDetail);
        exercicesPanel.initComponents(customExercices,
            "Exercices Custom",
            ce -> ce.getExercice().getName() + " by " + ce.getUser().getName()
        );
        mainPanel.add(new JScrollPane(exercicesPanel), BorderLayout.CENTER);

        add(mainPanel);
    }

    private JPanel createNavBar() {
        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        backButton = new JButton("Retour");
        backButton.addActionListener(e -> {
            new DashboardView().setVisible(true);
            dispose();
        });
        navBar.add(backButton);

        return navBar;
    }

    private void openExerciceDetail(ActionEvent event) {
        CustomExerciceDetailsDTO customExercice = (CustomExerciceDetailsDTO) event.getSource();
        new ExerciceDetailView(customExercice.getExercice()).setVisible(true);
        dispose();
    }
}
