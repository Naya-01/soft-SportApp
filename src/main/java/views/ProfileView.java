package views;

import controllers.UserController;
import models.domains.UserViewDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import utils.Log;
import views.utils.BaseView;
import views.utils.UserStore;

public class ProfileView extends BaseView {

    private UserController userController;
    private UserViewDTO currentUser;
    private Logger logger;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton saveButton;
    private JButton editButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    public ProfileView() {
        this.userController = new UserController();
        this.currentUser = UserStore.getCurrentUser();
        logger = Log.getLogger();
        logger.info("Profile view rendered");
        initComponents();
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

        saveButton = new JButton("Save Changes");
        saveButton.setBackground(new Color(59, 89, 182));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.addActionListener(new ActionListener() {
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
        gbc.gridwidth = 2;
        panel.add(saveButton, gbc);

        usernameLabel.setVisible(false);
        usernameField.setVisible(false);
        passwordLabel.setVisible(false);
        passwordField.setVisible(false);
        saveButton.setVisible(false);

        add(panel, BorderLayout.CENTER);
    }

    private void switchToEditMode() {
        editButton.setVisible(false);
        usernameLabel.setVisible(true);
        usernameField.setVisible(true);
        passwordLabel.setVisible(true);
        passwordField.setVisible(true);
        saveButton.setVisible(true);
    }

    private void saveChanges() {
        String newUsername = usernameField.getText();
        String newPassword = new String(passwordField.getPassword());

        if (newUsername.isEmpty() || newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                userController.updateProfile(newUsername, newPassword);
                currentUser.setName(newUsername);
                JOptionPane.showMessageDialog(this, "Profile updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error updating profile: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void onFeatureStateChanged(String featureName, boolean isActive) {

    }
}
