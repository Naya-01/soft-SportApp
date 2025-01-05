package views;

import controllers.ExerciceController;
import models.domains.*;
import models.enums.Difficulty;
import models.enums.ExerciceType;
import models.enums.MediaType;
import utils.Log;
import views.utils.BaseView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CustomExerciceCreationView extends BaseView {

    private ExerciceController exerciceController;
    private Logger logger;

    private JTextField nameField;
    private JTextArea explanationField;
    private JComboBox<ExerciceType> typeComboBox;
    private JComboBox<Difficulty> difficultyComboBox;
    private JLabel param1Label;
    private JTextField param1Field;
    private JLabel param2Label;
    private JTextField param2Field;
    private JTextField imageField;
    private JTextField videoField;
    private JButton saveButton;

    public CustomExerciceCreationView() {
        this.exerciceController = new ExerciceController();
        logger = Log.getLogger();
        logger.info("Custom Exercise Creation view rendered");
        initComponents();
    }

    private void initComponents() {
        setTitle("Custom Exercise Creation");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel navBar = createNavBar();
        mainPanel.add(navBar, BorderLayout.NORTH);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        nameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(nameField, gbc);

        JLabel explanationLabel = new JLabel("Explanation:");
        explanationLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(explanationLabel, gbc);

        explanationField = new JTextArea(5, 20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(new JScrollPane(explanationField), gbc);

        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(typeLabel, gbc);

        typeComboBox = new JComboBox<>(ExerciceType.values());
        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateParamFieldsVisibility();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(typeComboBox, gbc);

        JLabel difficultyLabel = new JLabel("Difficulty:");
        difficultyLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(difficultyLabel, gbc);

        difficultyComboBox = new JComboBox<>(Difficulty.values());
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(difficultyComboBox, gbc);

        param1Label = new JLabel("Parameter 1:");
        param1Label.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(param1Label, gbc);

        param1Field = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(param1Field, gbc);

        param2Label = new JLabel("Parameter 2:");
        param2Label.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(param2Label, gbc);

        param2Field = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(param2Field, gbc);

        JLabel imageLabel = new JLabel("Image URL:");
        imageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(imageLabel, gbc);

        imageField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(imageField, gbc);

        JLabel videoLabel = new JLabel("Video URL:");
        videoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(videoLabel, gbc);

        videoField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(videoField, gbc);

        saveButton = new JButton("Save Exercise");
        saveButton.setBackground(new Color(59, 89, 182));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveExercice();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(saveButton, gbc);

        mainPanel.add(panel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        updateParamFieldsVisibility();
    }

    private void updateParamFieldsVisibility() {
        ExerciceType selectedType = (ExerciceType) typeComboBox.getSelectedItem();
        boolean showParamFields = selectedType == ExerciceType.CARDIO || selectedType == ExerciceType.STRENGTH;

        param1Field.setVisible(showParamFields);
        param2Field.setVisible(showParamFields);

        if (selectedType == ExerciceType.CARDIO) {
            param1Label.setText("Duration:");
            param2Label.setText("Distance:");
        } else if (selectedType == ExerciceType.STRENGTH) {
            param1Label.setText("Repetitions:");
            param2Label.setText("Series:");
        }

        param1Label.setVisible(showParamFields);
        param2Label.setVisible(showParamFields);
    }

    private void saveExercice() {
        String name = nameField.getText();
        String explanation = explanationField.getText();
        ExerciceType type = (ExerciceType) typeComboBox.getSelectedItem();
        Difficulty difficulty = (Difficulty) difficultyComboBox.getSelectedItem();
        boolean isCustom = true;

        if (name.isEmpty() || explanation.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name and explanation cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<MediaDTO> medias = new ArrayList<>();
        if (!imageField.getText().isEmpty()) {
            medias.add(new MediaDTO(imageField.getText(), MediaType.IMAGE));
        }
        if (!videoField.getText().isEmpty()) {
            medias.add(new MediaDTO(videoField.getText(), MediaType.VIDEO));
        }

        try {
            ExerciceDTO exercice;
            if (type == ExerciceType.CARDIO) {
                int duration = Integer.parseInt(param1Field.getText());
                int distance = Integer.parseInt(param2Field.getText());
                exercice = exerciceController.addExercice(new ExerciceDTO(name, type, explanation, medias, difficulty, isCustom), duration, distance);
            } else if (type == ExerciceType.STRENGTH) {
                int repetitions = Integer.parseInt(param1Field.getText());
                int series = Integer.parseInt(param2Field.getText());
                exercice = exerciceController.addExercice(new ExerciceDTO(name, type, explanation, medias, difficulty, isCustom), repetitions, series);
            } else {
                exercice = exerciceController.addExercice(new ExerciceDTO(name, type, explanation, medias, difficulty, isCustom));
            }

            if (exercice != null) {
                JOptionPane.showMessageDialog(this, "Exercise created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                new DashboardView().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error creating exercise.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid parameters for the selected exercise type.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createNavBar() {
        JPanel navBar = new JPanel();
        navBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton backButton = new JButton("Back");
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
