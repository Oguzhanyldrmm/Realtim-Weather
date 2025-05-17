package view;

import javax.swing.*;
import java.awt.*;

public class TrackedCitiesPanel extends JPanel {

    private JTextArea trackedArea;

    public TrackedCitiesPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Tracked Cities"));

        trackedArea = new JTextArea(5, 30);
        trackedArea.setEditable(false);
        trackedArea.setFont(new Font("SansSerif", Font.PLAIN, 13));

        add(new JScrollPane(trackedArea), BorderLayout.CENTER);
    }

    public void updateTrackedWeather(String text) {
        trackedArea.setText(text);
    }
}
