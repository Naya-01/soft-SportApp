package views;

import controllers.ExerciceController;
import models.domains.UserViewDTO;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

import utils.Log;
import views.utils.BaseView;
import views.utils.UserStore;

public class ProfileView extends BaseView {

    private ExerciceController exerciceController;
    private UserViewDTO currentUser;
    private Logger logger;

    public ProfileView(ExerciceController exerciceController) {
        this.exerciceController = exerciceController;
        this.currentUser = UserStore.getCurrentUser();
        logger = Log.getLogger();
        logger.info("Profile view rendered");
        initComponents();
    }

    private void initComponents() {
        setTitle("User Profile");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Header Label
        JLabel headerLabel = new JLabel("User Profile");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(headerLabel, gbc);

        // Name Label
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(nameLabel, gbc);

        JLabel nameValueLabel = new JLabel(currentUser.getName());
        nameValueLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(nameValueLabel, gbc);

        // Premium Label
        JLabel premiumLabel = new JLabel("Is Premium:");
        premiumLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(premiumLabel, gbc);

        JLabel premiumValueLabel = new JLabel(currentUser.getPremium() ? "Yes" : "No");
        premiumValueLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(premiumValueLabel, gbc);

        // Edit Profile Button
        JButton editButton = new JButton("Edit Profile");
        editButton.setBackground(new Color(59, 89, 182));
        editButton.setForeground(Color.WHITE);
        editButton.setFocusPainted(false);
        editButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(editButton, gbc);

        add(panel, BorderLayout.CENTER);
    }
}
