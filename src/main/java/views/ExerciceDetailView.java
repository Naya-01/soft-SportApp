package views;

import controllers.TimerController;
import models.domains.ExerciceDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExerciceDetailView extends JFrame {

    private ExerciceDTO exercice;
    private TimerController timerController;
    private JLabel timerLabel;
    private javax.swing.Timer swingTimer;

    public ExerciceDetailView(ExerciceDTO exercice) {
        this.exercice = exercice;
        this.timerController = new TimerController();
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
        mainPanel.add(new JScrollPane(exerciceDetailsPanel), BorderLayout.CENTER);

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

        exerciceDetailsPanel.add(new JLabel("Nom : " + exercice.getName()));
        exerciceDetailsPanel.add(new JLabel("Type : " + exercice.getType()));
        exerciceDetailsPanel.add(new JLabel("Explication : " + exercice.getExplanation()));
        exerciceDetailsPanel.add(new JLabel("Difficulté : " + exercice.getDifficulty()));

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
            long elapsedTime = timerController.getElapsedTime();
            long seconds = (elapsedTime / 1000) % 60;
            long minutes = (elapsedTime / 1000) / 60;
            timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
        }
    }
}
