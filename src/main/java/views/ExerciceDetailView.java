package views;

import controllers.ExerciceController;
import controllers.PerformanceController;
import controllers.TimerController;
import models.domains.MediaDTO;
import models.domains.ExerciceDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Logger;

import utils.Log;
import views.utils.BaseView;

public class ExerciceDetailView extends BaseView {

    private ExerciceDTO exercice;
    private TimerController timerController;
    private ExerciceController exerciceController;
    private PerformanceController performanceController;
    private Logger logger;

    private JLabel timerLabel;
    private javax.swing.Timer swingTimer;

    private JTextArea performanceTextArea;
    private JButton editPerformanceButton;
    private JButton savePerformanceButton;
    private JButton cancelPerformanceButton;

    private String originalPerformanceText;

    public ExerciceDetailView(ExerciceDTO exercice) {
        this.exercice = exercice;
        this.timerController = new TimerController();
        this.exerciceController = new ExerciceController();
        this.performanceController = new PerformanceController();
        logger = Log.getLogger();
        logger.info("Exercice detail view rendered");
        initComponents();
    }

    private void initComponents() {
        setTitle("Exercice Details - " + exercice.getName());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel navBar = createNavBar();
        JPanel exerciceDetailsPanel = createExerciceDetailsPanel();

        mainPanel.add(navBar, BorderLayout.NORTH);
        mainPanel.add(exerciceDetailsPanel, BorderLayout.CENTER);

        if (timerController.isTimerActive()) {
            JPanel timerPanel = createTimerPanel();
            mainPanel.add(timerPanel, BorderLayout.SOUTH);
        }

        add(mainPanel);
    }

    private JPanel createNavBar() {
        JPanel navBar = new JPanel();
        navBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton backButton = new JButton("Retour");
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

    private JPanel createExerciceDetailsPanel() {
        JPanel exerciceDetailsPanel = new JPanel();
        exerciceDetailsPanel.setLayout(new BoxLayout(exerciceDetailsPanel, BoxLayout.Y_AXIS));

        exerciceDetailsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        exerciceDetailsPanel.add(new JLabel("Nom : " + exercice.getName()));
        exerciceDetailsPanel.add(new JLabel("Type : " + exercice.getType()));
        exerciceDetailsPanel.add(new JLabel("Explication : " + exercice.getExplanation()));
        exerciceDetailsPanel.add(new JLabel("Difficulté : " + exercice.getDifficulty()));

        List<MediaDTO> filteredImages = exerciceController.getFilteredImages(exercice.getMedias());
        if (!filteredImages.isEmpty()) {
            exerciceDetailsPanel.add(new JLabel("Images :"));
            for (MediaDTO media : filteredImages) {
                exerciceDetailsPanel.add(new JLabel(media.getUrl()));
            }
        }

        List<MediaDTO> filteredVideos = exerciceController.getFilteredVideos(exercice.getMedias());
        if (!filteredVideos.isEmpty()) {
            exerciceDetailsPanel.add(new JLabel("Vidéos :"));
            for (MediaDTO media : filteredVideos) {
                exerciceDetailsPanel.add(new JLabel(media.getUrl()));
            }
        }

        String performanceText = performanceController.getExercicePerformanceTextOfUser(exercice.getId().toString());
        originalPerformanceText = performanceText;

        exerciceDetailsPanel.add(new JLabel("Performance :"));

        JLabel performanceLabel = new JLabel(performanceText);
        exerciceDetailsPanel.add(performanceLabel);

        performanceTextArea = new JTextArea(5, 20);
        performanceTextArea.setText(performanceText);
        performanceTextArea.setEditable(false);
        performanceTextArea.setLineWrap(true);
        performanceTextArea.setWrapStyleWord(true);
        performanceTextArea.setPreferredSize(new Dimension(400, 100));
        performanceTextArea.setVisible(false);
        exerciceDetailsPanel.add(new JScrollPane(performanceTextArea));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

        editPerformanceButton = new JButton("Edit performance");
        editPerformanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performanceLabel.setVisible(false);
                performanceTextArea.setVisible(true);
                performanceTextArea.setEditable(true);
                performanceTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                editPerformanceButton.setVisible(false);
                savePerformanceButton.setVisible(true);
                cancelPerformanceButton.setVisible(true);
            }
        });

        savePerformanceButton = new JButton("Save performance");
        savePerformanceButton.setVisible(false);
        savePerformanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String updatedPerformanceText = performanceTextArea.getText();
                performanceController.addExercicePerformance(exercice.getId().toString(), updatedPerformanceText);
                originalPerformanceText = updatedPerformanceText;
                performanceTextArea.setEditable(false);
                performanceTextArea.setVisible(false);
                performanceLabel.setText(updatedPerformanceText);
                performanceLabel.setVisible(true);

                savePerformanceButton.setVisible(false);
                cancelPerformanceButton.setVisible(false);
                editPerformanceButton.setVisible(true);
            }
        });

        cancelPerformanceButton = new JButton("Cancel modifications");
        cancelPerformanceButton.setVisible(false);
        cancelPerformanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performanceTextArea.setText(originalPerformanceText);
                performanceTextArea.setEditable(false);
                performanceTextArea.setVisible(false);
                performanceLabel.setText(originalPerformanceText);
                performanceLabel.setVisible(true);

                savePerformanceButton.setVisible(false);
                cancelPerformanceButton.setVisible(false);
                editPerformanceButton.setVisible(true);
            }
        });

        buttonPanel.add(editPerformanceButton);
        buttonPanel.add(savePerformanceButton);
        buttonPanel.add(cancelPerformanceButton);
        exerciceDetailsPanel.add(buttonPanel);

        return exerciceDetailsPanel;
    }

    private JPanel createTimerPanel() {
        JPanel timerPanel = new JPanel();
        timerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        timerLabel = new JLabel("00:00");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton startButton = new JButton("Démarrer");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer();
                startSwingTimer();
            }
        });

        JButton stopButton = new JButton("Arrêter");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopSwingTimer();
            }
        });

        JButton resetButton = new JButton("Réinitialiser");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTimer();
            }
        });

        timerPanel.add(timerLabel);
        timerPanel.add(startButton);
        timerPanel.add(stopButton);
        timerPanel.add(resetButton);

        return timerPanel;
    }

    private void startTimer() {
        if (!timerController.isTimerRunning()) {
            timerController.startTimer();
            timerController.setTimerRunning(true);
        }
    }

    private void resetTimer() {
        timerController.resetTimer();
        timerController.setTimerRunning(false);
        timerLabel.setText("00:00");
    }

    private void startSwingTimer() {
        if (swingTimer == null) {
            swingTimer = new javax.swing.Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateTimerLabel();
                }
            });
        }
        swingTimer.start();
    }

    private void stopSwingTimer() {
        if (swingTimer != null) {
            swingTimer.stop();
        }
        timerController.setTimerRunning(false);
    }

    private void updateTimerLabel() {
        if (timerController.getStartTime() > 0) {
            long elapsed = (System.currentTimeMillis() - timerController.getStartTime()) / 1000;
            long minutes = elapsed / 60;
            long seconds = elapsed % 60;
            timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
        }
    }
}
