package view;

import javax.swing.*;
import java.awt.*;

public class UnitSelectorPanel extends JPanel {

    private JRadioButton celsiusButton;
    private JRadioButton fahrenheitButton;
    private ButtonGroup unitGroup;

    public UnitSelectorPanel() {
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createTitledBorder("Temperature Unit"));

        celsiusButton = new JRadioButton("Celsius (°C)", true);
        fahrenheitButton = new JRadioButton("Fahrenheit (°F)");

        unitGroup = new ButtonGroup();
        unitGroup.add(celsiusButton);
        unitGroup.add(fahrenheitButton);

        add(celsiusButton);
        add(fahrenheitButton);
    }

    public boolean isCelsiusSelected() {
        return celsiusButton.isSelected();
    }

    public boolean isFahrenheitSelected() {
        return fahrenheitButton.isSelected();
    }
}
