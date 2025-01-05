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
import models.domains.CustomExerciceDetailsDTO;
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
import views.components.ExercicesPanel;
import views.components.NavBar;
import views.components.PaymentDialog;
import views.utils.BaseView;
import views.utils.UserStore;

public class DashboardView extends BaseView implements UIViewObserver {

    private ExerciceController exerciceController;
    private PaymentMethodController paymentMethodController;
    private FeatureManager featureManager;
    private JComboBox<Difficulty> difficultyComboBox;
    private JCheckBox cardioCheckBox;
    private JCheckBox strengthCheckBox;
    private JCheckBox flexibilityCheckBox;

    private JPanel mainPanel;
    private JPanel navBar;
    private JPanel filterPanel;
    private JPanel exercicesPanel;

    private NavBar navBarComponent;

    public DashboardView() {
        super();
        this.exerciceController = new ExerciceController();
        this.paymentMethodController = new PaymentMethodController();
        this.featureManager = FeatureManager.getInstance();
        logger = Log.getLogger();
        logger.info("Dashboard view rendered");

        navBarComponent = new NavBar( this);
        initComponents();
        featureManager.addObserver(this);
        initFeatureUpdateHandlers();
        initializeFeatureStates();
    }

    private void initComponents() {
        setTitle("Dashboard - Exercises");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());
        navBar = navBarComponent.createNavBar();
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
        featureUpdateHandlers.put(FeaturesEnum.EXERCICE_DIFFICULTY_BEGINNER.getFeature(), this::toggleDifficulty);
        featureUpdateHandlers.put(FeaturesEnum.EXERCICE_DIFFICULTY_INTERMEDIATE.getFeature(), this::toggleDifficulty);
        featureUpdateHandlers.put(FeaturesEnum.EXERCICE_DIFFICULTY_ADVANCED.getFeature(), this::toggleDifficulty);

        featureUpdateHandlers.put(FeaturesEnum.PROFILE.getFeature(), this::toggleProfileButton);
        featureUpdateHandlers.put(FeaturesEnum.PREMIUM.getFeature(), this::togglePremiumButton);
        featureUpdateHandlers.put(FeaturesEnum.COMMUNITY.getFeature(), this::toggleCommunityButton);
        featureUpdateHandlers.put(FeaturesEnum.EXERCISE.getFeature(), this::toggleExercisePanel);
        featureUpdateHandlers.put(FeaturesEnum.EXERCICE_CUSTOM_ADD.getFeature(), this::toggleCustomExerciceButton);
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

    private void toggleDifficulty(boolean isActive) {
        logger.info("Toggling difficulty " +exerciceController.getCurrentDifficulty());
        difficultyComboBox.setSelectedItem(exerciceController.getCurrentDifficulty());
        refreshExercicesPanel();
    }

    private void toggleCardioCheckbox(boolean isActive) {
        logger.info("Toggling cardio checkbox ");
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
        logger.info("Toggling strength checkbox");
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
        logger.info("Toggling flexibility checkbox ");
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
        logger.info("Toggling profile button ");
        if (navBarComponent.getProfileButton() != null) {
            navBarComponent.getProfileButton().setVisible(isActive);
        }
    }

    private void togglePremiumButton(boolean isActive) {
        logger.info("Toggling premium button ");
        if (navBarComponent.getPremiumButton() != null) {
            if (isActive && !UserStore.getCurrentUser().getPremium()) {
                navBarComponent.getPremiumButton().setVisible(true);
            } else {
                navBarComponent.getPremiumButton().setVisible(false);
            }
        }
    }

    private void toggleCommunityButton(boolean isActive) {
        logger.info("Toggling community button ");
        if (navBarComponent.getCommunityButton() != null) {
            navBarComponent.getCommunityButton() .setVisible(isActive);
        }
    }


    private void toggleCustomExerciceButton(boolean isActive) {
        logger.info("Toggling community button ");
        if (navBarComponent.getCustomExerciceButton() != null) {
            navBarComponent.getCustomExerciceButton().setVisible(isActive);
        }
    }

    private void toggleExercisePanel(boolean isActive) {
        logger.info("Toggling exercise panel ");
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

    private JPanel createFilterPanel() {
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filters"));

        JLabel difficultyLabel = new JLabel("Filter by Difficulty:");
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

        JLabel typeLabel = new JLabel("Filter by Type:");
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
        ExercicesPanel exercicesPanel = new ExercicesPanel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExerciceDTO exercice = (ExerciceDTO) e.getSource();
                new ExerciceDetailView(exercice).setVisible(true);
                dispose();
            }
        });

        List<ExerciceDTO> exercices = exerciceController.getAllNoCustomExercices();
        if (exercices != null) {
            exercicesPanel.initComponents(exercices,
                "Exercises",
                ExerciceDTO::getName);
        }

        return exercicesPanel;
    }

    private void refreshExercicesPanel() {
        getContentPane().removeAll();

        mainPanel = new JPanel(new BorderLayout());
        navBar = navBarComponent.createNavBar();
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

}
