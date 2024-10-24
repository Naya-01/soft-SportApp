package views;

import controllers.ExerciceController;
import controllers.PaymentMethodController;
import controllers.UserController;
import models.PaymentMethod.PaymentMethod;
import models.domains.ExerciceDTO;
import models.enums.Difficulty;
import models.enums.ExerciceType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import views.utils.UserStore;
import features.managers.ViewManager;

public class DashboardView extends JFrame {

    private ExerciceController exerciceController;
    private UserController userController;
    private PaymentMethodController paymentMethodController;
    private ViewManager viewManager;

    private JComboBox<Difficulty> difficultyComboBox;
    private JCheckBox cardioCheckBox;
    private JCheckBox strengthCheckBox;
    private JCheckBox flexibilityCheckBox;
    private JButton premiumButton;

    public DashboardView() {
        this.exerciceController = new ExerciceController();
        this.userController = new UserController();
        this.paymentMethodController = new PaymentMethodController();
        this.viewManager = ViewManager.getInstance();
        initComponents();
    }

    private void initComponents() {
        setTitle("Dashboard - Exercices");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel navBar = createNavBar();
        JPanel filterPanel = createFilterPanel();
        JPanel exercicesPanel = createExercicesPanel();

        mainPanel.add(navBar, BorderLayout.NORTH);

        if (viewManager.isActive("exercice")) {
            mainPanel.add(new JScrollPane(exercicesPanel), BorderLayout.CENTER);
        }

        if (viewManager.isActive("exercice_filter")) {
            mainPanel.add(filterPanel, BorderLayout.WEST);
        }

        add(mainPanel);
    }

    private JPanel createNavBar() {
        JPanel navBar = new JPanel();
        navBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        if (viewManager.isActive("account")) {
            JButton profileButton = new JButton("Voir Profil");
            profileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ProfileView(exerciceController).setVisible(true);
                }
            });
            navBar.add(profileButton);
        }

        if (viewManager.isActive("payment_methods")) {
            premiumButton = new JButton("Become Premium");
            premiumButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showPaymentDialog();
                }
            });

            premiumButton.setVisible(!UserStore.getCurrentUser().getPremium());
            navBar.add(premiumButton);
        }

        if (viewManager.isActive("community")) {
            JButton communityButton = new JButton("Community");
            communityButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new CommunityView().setVisible(true);
                    dispose();
                }
            });
            navBar.add(communityButton);
        }

        return navBar;
    }

    private JPanel createFilterPanel() {
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filtres"));

        JLabel difficultyLabel = new JLabel("Filtrer par Difficulté:");
        filterPanel.add(difficultyLabel);

        difficultyComboBox = new JComboBox<>(Difficulty.values());
        difficultyComboBox.setSelectedItem(exerciceController.getCurrentDifficulty());
        difficultyComboBox.setPreferredSize(new Dimension(125, 25));
        difficultyComboBox.setMaximumSize(new Dimension(200, 25));

        difficultyComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exerciceController.setExerciceDifficulty((Difficulty) difficultyComboBox.getSelectedItem());
                refreshExercicesPanel();
            }
        });
        filterPanel.add(difficultyComboBox);

        // Filtre par type d'exercice
        JLabel typeLabel = new JLabel("Filtrer par Type:");
        filterPanel.add(typeLabel);

        cardioCheckBox = new JCheckBox("Cardio");
        cardioCheckBox.setSelected(exerciceController.getCurrentTypes().contains(ExerciceType.CARDIO));
        cardioCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateExerciceTypes();
            }
        });
        filterPanel.add(cardioCheckBox);

        strengthCheckBox = new JCheckBox("Strength");
        strengthCheckBox.setSelected(exerciceController.getCurrentTypes().contains(ExerciceType.STRENGTH));
        strengthCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateExerciceTypes();
            }
        });
        filterPanel.add(strengthCheckBox);

        flexibilityCheckBox = new JCheckBox("Flexibility");
        flexibilityCheckBox.setSelected(exerciceController.getCurrentTypes().contains(ExerciceType.FLEXIBILITY));
        flexibilityCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateExerciceTypes();
            }
        });
        filterPanel.add(flexibilityCheckBox);

        return filterPanel;
    }

    private JPanel createExercicesPanel() {
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

        return exercicesPanel;
    }

    private void refreshExercicesPanel() {
        getContentPane().removeAll();

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel navBar = createNavBar();
        JPanel filterPanel = createFilterPanel();
        JPanel exercicesPanel = createExercicesPanel();

        mainPanel.add(navBar, BorderLayout.NORTH);
        mainPanel.add(filterPanel, BorderLayout.WEST);
        mainPanel.add(new JScrollPane(exercicesPanel), BorderLayout.CENTER);

        add(mainPanel);
        revalidate();
        repaint();
    }

    private void updateExerciceTypes() {
        List<ExerciceType> selectedTypes = new ArrayList<>();
        if (cardioCheckBox.isSelected()) {
            selectedTypes.add(ExerciceType.CARDIO);
        }
        if (strengthCheckBox.isSelected()) {
            selectedTypes.add(ExerciceType.STRENGTH);
        }
        if (flexibilityCheckBox.isSelected()) {
            selectedTypes.add(ExerciceType.FLEXIBILITY);
        }

        exerciceController.setExerciceTypes(selectedTypes);
        refreshExercicesPanel();
    }

    private void showPaymentDialog() {
        List<PaymentMethod> availableMethods = paymentMethodController.getAvailablePaymentMethods();
        if (availableMethods.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No payment methods are available.");
            return;
        }

        String[] options = availableMethods.stream().map(PaymentMethod::getName).toArray(String[]::new);
        int choice = JOptionPane.showOptionDialog(
            this,
            "Choose a payment method:",
            "Payment Method",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]
        );

        if (choice != -1) {
            PaymentMethod selectedMethod = availableMethods.get(choice);
            boolean success = paymentMethodController.upgradeAccount(selectedMethod);
            if (success) {
                JOptionPane.showMessageDialog(this, "Payment successful! You are now a premium user.");
                UserStore.getCurrentUser().setPremium(true);

                premiumButton.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Payment failed. Please try again.");
            }
        }
    }
}
