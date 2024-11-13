package views;

import controllers.UserController;
import features.FeaturesEnum;
import features.managers.FeatureManager;
import models.domains.UserViewDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Logger;

import utils.Log;
import views.utils.BaseView;
import views.utils.UserStore;

public class ProfileView extends BaseView {

    private UserController userController;
    private FeatureManager featureManager;
    private UserViewDTO currentUser;
    private Logger logger;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton saveChangesButton;
    private JButton editButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    public ProfileView() {
        this.userController = new UserController();
        this.currentUser = UserStore.getCurrentUser();
        this.featureManager = FeatureManager.getInstance();
        logger = Log.getLogger();
        logger.info("Profile view rendered");
        initComponents();
        featureManager.addObserver(this);
        initFeatureUpdateHandlers();
        initializeFeatureStates();
    }

    private void initFeatureUpdateHandlers() {
        featureUpdateHandlers = new HashMap<>();

        featureUpdateHandlers.put(FeaturesEnum.EXERCICE_TYPE_CARDIO.getFeature(), this::toggleCardioCheckbox);
        featureUpdateHandlers.put(FeaturesEnum.EXERCICE_TYPE_STRENGTH.getFeature(), this::toggleStrengthCheckbox);
        featureUpdateHandlers.put(FeaturesEnum.EXERCICE_TYPE_FLEXIBILITY.getFeature(), this::toggleFlexibilityCheckbox);

        featureUpdateHandlers.put(FeaturesEnum.PROFILE.getFeature(), this::toggleProfileButton);
        featureUpdateHandlers.put(FeaturesEnum.PREMIUM.getFeature(), this::togglePremiumButton);
        featureUpdateHandlers.put(FeaturesEnum.COMMUNITY.getFeature(), this::toggleCommunityButton);
        featureUpdateHandlers.put(FeaturesEnum.EXERCISE.getFeature(), this::toggleExercisePanel);
    }

    private void initializeFeatureStates() {
        for (Map.Entry<String, Consumer<Boolean>> entry : featureUpdateHandlers.entrySet()) {
            String featureName = entry.getKey();
            boolean isActive = featureManager.isActive(featureName);
            Consumer<Boolean> handler = entry.getValue();
            handler.accept(isActive);
        }
    }

    private void initComponents() {
        setTitle("User Profile");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel headerLabel = new JLabel("User Profile");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(headerLabel, gbc);

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

        editButton = new JButton("Edit Profile");
        editButton.setBackground(new Color(59, 89, 182));
        editButton.setForeground(Color.WHITE);
        editButton.setFocusPainted(false);
        editButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(editButton, gbc);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToEditMode();
            }
        });

        usernameLabel = new JLabel("New Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField = new JTextField(currentUser.getName(), 20);
        usernameField.setPreferredSize(new Dimension(200, 25));

        passwordLabel = new JLabel("New Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(200, 25));

        saveChangesButton = new JButton("Save changes");
        saveChangesButton.setBackground(new Color(59, 89, 182));
        saveChangesButton.setForeground(Color.WHITE);
        saveChangesButton.setFocusPainted(false);
        saveChangesButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        panel.add(saveChangesButton, gbc);

        usernameLabel.setVisible(false);
        usernameField.setVisible(false);
        passwordLabel.setVisible(false);
        passwordField.setVisible(false);
        saveChangesButton.setVisible(false);

        add(panel, BorderLayout.CENTER);
    }

    private void switchToEditMode() {
        editButton.setVisible(false);
        usernameLabel.setVisible(true);
        usernameField.setVisible(true);
        passwordLabel.setVisible(true);
        passwordField.setVisible(true);
        saveChangesButton.setVisible(true);
    }

    private void saveChanges() {
        String newUsername = usernameField.getText();
        String newPassword = new String(passwordField.getPassword());

        if (newUsername.isEmpty() || newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username or password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                userController.setUsername(newUsername);
                userController.setPassword(newPassword);
                currentUser.setName(newUsername);
                JOptionPane.showMessageDialog(this, "Username or password updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error updating username or password: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
