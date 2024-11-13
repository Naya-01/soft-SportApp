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
    private JButton editUsernameButton;
    private JButton editPasswordButton;
    private JButton saveChangesButton;

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

        featureUpdateHandlers.put(FeaturesEnum.SET_PASSWORD.getFeature(), this::togglePasswordButton);
        featureUpdateHandlers.put(FeaturesEnum.SET_USERNAME.getFeature(), this::toggleUsernameButton);
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
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(headerLabel, gbc);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(nameLabel, gbc);

        usernameField = new JTextField(currentUser.getName());
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setEditable(false);
        usernameField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(usernameField, gbc);

        editUsernameButton = new JButton("Edit");
        editUsernameButton.setFont(new Font("Arial", Font.PLAIN, 14));
        editUsernameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameField.isEditable()) {
                    usernameField.setText(currentUser.getName());
                    usernameField.setEditable(false);
                    editUsernameButton.setText("Edit");
                } else {
                    usernameField.setEditable(true);
                    editUsernameButton.setText("Cancel");
                }
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(editUsernameButton, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField("");
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setEditable(false);
        usernameField.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passwordField, gbc);

        editPasswordButton = new JButton("Edit");
        editPasswordButton.setFont(new Font("Arial", Font.PLAIN, 14));
        editPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordField.isEditable()) {
                    passwordField.setText("");
                    passwordField.setEditable(false);
                    editPasswordButton.setText("Edit");
                } else {
                    passwordField.setEditable(true);
                    editPasswordButton.setText("Cancel");
                }
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(editPasswordButton, gbc);

        JLabel premiumLabel = new JLabel("Is Premium:");
        premiumLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(premiumLabel, gbc);

        JLabel premiumValueLabel = new JLabel(currentUser.getPremium() ? "Yes" : "No");
        premiumValueLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(premiumValueLabel, gbc);

        saveChangesButton = new JButton("Save Changes");
        saveChangesButton.setFont(new Font("Arial", Font.PLAIN, 14));
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        panel.add(saveChangesButton, gbc);

        add(panel, BorderLayout.CENTER);
    }

    private void toggleUsernameButton(boolean isActive) {
        logger.info("Toggling username button to " + isActive);
        if (editUsernameButton != null) {
            editUsernameButton.setVisible(isActive);
        }
    }

    private void togglePasswordButton(boolean isActive) {
        logger.info("Toggling password button to " + isActive);
        if (editPasswordButton != null) {
            editPasswordButton.setVisible(isActive);
        }
    }

    private void saveChanges() {
        if (usernameField.isEditable() && featureManager.isActive(FeaturesEnum.SET_USERNAME.getFeature())) {
            setUsername();
        }
        if (passwordField.isEditable() && featureManager.isActive(FeaturesEnum.SET_PASSWORD.getFeature())) {
            setPassword();
        }
    }

    private void setUsername() {
        String newUsername = usernameField.getText();

        if (featureManager.isActive(FeaturesEnum.SET_USERNAME.getFeature()) && !newUsername.isEmpty()) {
            try {
                userController.setUsername(newUsername);
                currentUser.setName(newUsername);
                JOptionPane.showMessageDialog(this, "Username updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                usernameField.setEditable(false);
                editUsernameButton.setText("Edit");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error updating username: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void setPassword() {
        String newPassword = new String(passwordField.getPassword());

        if (featureManager.isActive(FeaturesEnum.SET_PASSWORD.getFeature()) && !newPassword.isEmpty()) {
            try {
                userController.setPassword(newPassword);
                JOptionPane.showMessageDialog(this, "Password updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                passwordField.setEditable(false);
                editPasswordButton.setText("Edit");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error updating password: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
