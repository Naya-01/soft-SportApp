package views.components;

import java.awt.event.ActionEvent;
import java.util.function.Function;
import models.domains.ExerciceDTO;
import models.domains.CustomExerciceDetailsDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ExercicesPanel extends JPanel {
    private ActionListener exerciceListener;

    public ExercicesPanel(ActionListener exerciceListener) {
        this.exerciceListener = exerciceListener;
    }

    public <T> void initComponents(List<T> exercices, String title, Function<T, String> buttonTextFunction) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder(title));

        for (T exercice : exercices) {
            JButton exerciceButton = new JButton(buttonTextFunction.apply(exercice));
            exerciceButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            exerciceButton.addActionListener(e -> exerciceListener.actionPerformed(
                new ActionEvent(exercice, ActionEvent.ACTION_PERFORMED, null)
            ));
            add(exerciceButton);
        }
    }
}
