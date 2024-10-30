package views;

import controllers.AuthController;
import models.domains.UserViewDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import utils.Log;
import views.utils.BaseView;
import views.utils.UserStore;

public class AuthView extends BaseView {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    private AuthController authController;
    private Logger logger;


    public AuthView(AuthController authController) {
        this.authController = authController;
        logger = Log.getLogger();
        logger.info("Auth view rendered");
        initComponents();
    }

    private void initComponents() {
        setTitle("Login / Register");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);  // Ajouter de l'espace entre les composants

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);

        usernameField = new JTextField(20);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(usernameField, constraints);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        passwordField = new JPasswordField(20);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(passwordField, constraints);

        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(59, 89, 182));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(loginButton, constraints);

        registerButton = new JButton("Register");
        registerButton.setBackground(new Color(34, 139, 34));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(registerButton, constraints);

        add(panel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        UserViewDTO user = authController.login(username, password);
        if (user != null) {
            UserStore.setCurrentUser(user);
            JOptionPane.showMessageDialog(this, "Login successful", "Success", JOptionPane.INFORMATION_MESSAGE);

            this.dispose();
            new DashboardView().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Login failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void handleRegister() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        boolean success = authController.register(username, password, false);
        if (success) {
            JOptionPane.showMessageDialog(this, "Registration successful", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
