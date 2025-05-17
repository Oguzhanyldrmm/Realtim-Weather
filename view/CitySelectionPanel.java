package view;

import javax.swing.*;
import java.awt.*;

public class CitySelectionPanel extends JPanel {

    private JComboBox<String> cityComboBox;
    private JSpinner dateSpinner;
    private JButton showButton;

    public CitySelectionPanel() {
        setLayout(new GridLayout(3, 1));
        setBorder(BorderFactory.createTitledBorder("Select City & Date"));

        cityComboBox = new JComboBox<>();
        cityComboBox.setEditable(false);

        // Tarih seçici
        SpinnerDateModel dateModel = new SpinnerDateModel();
        dateSpinner = new JSpinner(dateModel);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));

        showButton = new JButton("Show Weather");

        add(new JLabel("City:"));
        add(cityComboBox);
        add(new JLabel("Date:"));
        add(dateSpinner);
        add(showButton);
    }

    public JComboBox<String> getCityComboBox() {
        return cityComboBox;
    }

    public JSpinner getDateSpinner() {
        return dateSpinner;
    }

    public JButton getShowButton() {
        return showButton;
    }
}
