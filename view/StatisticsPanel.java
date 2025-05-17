package view;

import javax.swing.*;
import java.awt.*;

public class StatisticsPanel extends JPanel {

    private JTextArea statsArea;

    public StatisticsPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Weather Statistics"));

        statsArea = new JTextArea(6, 50);
        statsArea.setEditable(false);
        add(new JScrollPane(statsArea), BorderLayout.CENTER);
    }

    public void updateStatistics(String statsText) {
        statsArea.setText(statsText);
    }
}
