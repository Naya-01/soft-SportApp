package views;

import controllers.ExerciceController;
import controllers.PaymentMethodController;
import features.FeaturesEnum;
import features.managers.FeatureManager;
import features.observers.UIViewObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import models.PaymentMethod.PaymentMethod;
import models.domains.ExerciceDTO;
import models.enums.Difficulty;
import models.enums.ExerciceType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import java.util.logging.Logger;

import utils.Log;
import views.utils.BaseView;
import views.utils.UserStore;

public class DashboardView extends BaseView implements UIViewObserver {

    private ExerciceController exerciceController;
    private PaymentMethodController paymentMethodController;
    private FeatureManager featureManager;
    private Logger logger;

    private JComboBox<Difficulty> difficultyComboBox;
    private JCheckBox cardioCheckBox;
    private JCheckBox strengthCheckBox;
    private JCheckBox flexibilityCheckBox;

    private JPanel mainPanel;
    private JPanel navBar;
    private JPanel filterPanel;
    private JPanel exercicesPanel;

    private Map<String, Consumer<Boolean>> featureUpdateHandlers;

    private JButton profileButton;
    private JButton communityButton;
    private JButton premiumButton;
    private JButton customExerciceButton;

    public DashboardView() {
        super();
        this.exerciceController = new ExerciceController();
        this.paymentMethodController = new PaymentMethodController();
        this.featureManager = FeatureManager.getInstance();
        logger = Log.getLogger();
        logger.info("Dashboard view rendered");
        initComponents();
        featureManager.addObserver(this);
        initFeatureUpdateHandlers();
        initializeFeatureStates();
    }

    private void initComponents() {
        setTitle("Dashboard - Exercices");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());
        navBar = createNavBar();
        filterPanel = createFilterPanel();
        exercicesPanel = createExercicesPanel();

        mainPanel.add(navBar, BorderLayout.NORTH);
        mainPanel.add(filterPanel, BorderLayout.WEST);
        mainPanel.add(new JScrollPane(exercicesPanel), BorderLayout.CENTER);

        add(mainPanel);
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

    @Override
    public void onUIViewStateChanged(boolean enabled) {
        SwingUtilities.invokeLater(() -> setVisible(enabled));
    }

    @Override
    public void onFeatureStateChanged(String featureName, boolean isActive) {
        logger.info("Feature state changed: " + featureName + " to " + isActive);
        Consumer<Boolean> handler = featureUpdateHandlers.get(featureName);
        if (handler != null) {
            SwingUtilities.invokeLater(() -> handler.accept(isActive));
        }
    }

    private void toggleCardioCheckbox(boolean isActive) {
        logger.info("Toggling cardio checkbox to " + isActive);
        cardioCheckBox.setVisible(isActive);
        if (isActive) {
            cardioCheckBox.setSelected(true);
            exerciceController.getCurrentTypes().add(ExerciceType.CARDIO);
        } else {
            cardioCheckBox.setSelected(false);
            exerciceController.getCurrentTypes().remove(ExerciceType.CARDIO);
        }
        refreshExercicesPanel();
    }

    private void toggleStrengthCheckbox(boolean isActive) {
        logger.info("Toggling strength checkbox to " + isActive);
        strengthCheckBox.setVisible(isActive);
        if (isActive) {
            strengthCheckBox.setSelected(true);
            exerciceController.getCurrentTypes().add(ExerciceType.STRENGTH);
        } else {
            strengthCheckBox.setSelected(false);
            exerciceController.getCurrentTypes().remove(ExerciceType.STRENGTH);
        }
        refreshExercicesPanel();
    }

    private void toggleFlexibilityCheckbox(boolean isActive) {
        logger.info("Toggling flexibility checkbox to " + isActive);
        flexibilityCheckBox.setVisible(isActive);
        if (isActive) {
            flexibilityCheckBox.setSelected(true);
            exerciceController.getCurrentTypes().add(ExerciceType.FLEXIBILITY);
        } else {
            flexibilityCheckBox.setSelected(false);
            exerciceController.getCurrentTypes().remove(ExerciceType.FLEXIBILITY);
        }
        refreshExercicesPanel();
    }

    private void toggleProfileButton(boolean isActive) {
        logger.info("Toggling profile button to " + isActive);
        if (profileButton != null) {
            profileButton.setVisible(isActive);
        }
    }

    private void togglePremiumButton(boolean isActive) {
        logger.info("Toggling premium button to " + isActive);
        if (premiumButton != null) {
            if (isActive && !UserStore.getCurrentUser().getPremium()) {
                premiumButton.setVisible(true);
            } else {
                premiumButton.setVisible(false);
            }
        }
    }

    private void toggleCommunityButton(boolean isActive) {
        logger.info("Toggling community button to " + isActive);
        if (communityButton != null) {
            communityButton.setVisible(isActive);
        }
    }

    private void toggleExercisePanel(boolean isActive) {
        logger.info("Toggling exercise panel to " + isActive);
        filterPanel.setVisible(isActive);
        exercicesPanel.setVisible(isActive);

        if (!isActive) {
            cardioCheckBox.setSelected(false);
            strengthCheckBox.setSelected(false);
            flexibilityCheckBox.setSelected(false);
            exerciceController.getCurrentTypes().clear();
            refreshExercicesPanel();
        } else {
            cardioCheckBox.setSelected(exerciceController.getCurrentTypes().contains(ExerciceType.CARDIO));
            strengthCheckBox.setSelected(exerciceController.getCurrentTypes().contains(ExerciceType.STRENGTH));
            flexibilityCheckBox.setSelected(exerciceController.getCurrentTypes().contains(ExerciceType.FLEXIBILITY));
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JPanel createNavBar() {
        JPanel navBar = new JPanel();
        navBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        profileButton = new JButton("Voir Profil");
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfileView().setVisible(true);
            }
        });
        navBar.add(profileButton);

        premiumButton = new JButton("Become Premium");
        premiumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPaymentDialog();
            }
        });
        premiumButton.setVisible(!UserStore.getCurrentUser().getPremium());
        navBar.add(premiumButton);

        customExerciceButton = new JButton("Add Custom Exercice");
        customExerciceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomExerciceCreationView().setVisible(true);
                dispose();
            }
        });
        customExerciceButton.setVisible(UserStore.getCurrentUser().getPremium());
        navBar.add(customExerciceButton);

        communityButton = new JButton("Community");
        communityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CommunityView().setVisible(true);
                dispose();
            }
        });
        navBar.add(communityButton);

        return navBar;
    }

    private JPanel createFilterPanel() {
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filtres"));

        JLabel difficultyLabel = new JLabel("Filtrer par Difficulté:");
        filterPanel.add(difficultyLabel);

        List<Difficulty> activeDifficulties = new ArrayList<>();
        for (Difficulty difficulty : Difficulty.values()) {
            activeDifficulties.add(difficulty);
        }

        difficultyComboBox = new JComboBox<>(activeDifficulties.toArray(new Difficulty[0]));
        Difficulty currentDifficulty = exerciceController.getCurrentDifficulty();
        if (activeDifficulties.contains(currentDifficulty)) {
            difficultyComboBox.setSelectedItem(currentDifficulty);
        } else {
            if (!activeDifficulties.isEmpty()) {
                difficultyComboBox.setSelectedItem(activeDifficulties.get(0));
            } else {
                difficultyComboBox.setSelectedItem(null);
            }
        }
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

        JLabel typeLabel = new JLabel("Filtrer par Type:");
        filterPanel.add(typeLabel);

        cardioCheckBox = new JCheckBox(ExerciceType.CARDIO.getTypeName());
        cardioCheckBox.setSelected(exerciceController.getCurrentTypes().contains(ExerciceType.CARDIO));
        cardioCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateExerciceTypes();
            }
        });
        filterPanel.add(cardioCheckBox);

        strengthCheckBox = new JCheckBox(ExerciceType.STRENGTH.getTypeName());
        strengthCheckBox.setSelected(exerciceController.getCurrentTypes().contains(ExerciceType.STRENGTH));
        strengthCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateExerciceTypes();
            }
        });
        filterPanel.add(strengthCheckBox);

        flexibilityCheckBox = new JCheckBox(ExerciceType.FLEXIBILITY.getTypeName());
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

        mainPanel = new JPanel(new BorderLayout());
        navBar = createNavBar();
        filterPanel = createFilterPanel();
        exercicesPanel = createExercicesPanel();

        mainPanel.add(navBar, BorderLayout.NORTH);
        mainPanel.add(filterPanel, BorderLayout.WEST);
        mainPanel.add(new JScrollPane(exercicesPanel), BorderLayout.CENTER);

        add(mainPanel);
        revalidate();
        repaint();
    }

    private void updateExerciceTypes() {
        List<ExerciceType> selectedTypes = new ArrayList<>();
        if (cardioCheckBox != null && cardioCheckBox.isSelected()) {
            selectedTypes.add(ExerciceType.CARDIO);
        }
        if (strengthCheckBox != null && strengthCheckBox.isSelected()) {
            selectedTypes.add(ExerciceType.STRENGTH);
        }
        if (flexibilityCheckBox != null && flexibilityCheckBox.isSelected()) {
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
