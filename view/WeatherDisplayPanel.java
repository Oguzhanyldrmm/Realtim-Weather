package view;

import javax.swing.*;
import java.awt.*;

public class WeatherDisplayPanel extends JPanel {

    private JLabel temperatureLabel;
    private JLabel humidityLabel;
    private JLabel windSpeedLabel;
    private JLabel conditionLabel;

    public WeatherDisplayPanel() {
        setLayout(new GridLayout(4, 1));
        setBorder(BorderFactory.createTitledBorder("Weather Info"));

        temperatureLabel = new JLabel("Temperature: ");
        humidityLabel = new JLabel("Humidity: ");
        windSpeedLabel = new JLabel("Wind Speed: ");
        conditionLabel = new JLabel("Condition: ");

        add(temperatureLabel);
        add(humidityLabel);
        add(windSpeedLabel);
        add(conditionLabel);
    }

    public void updateWeatherInfo(String temp, String humidity, String wind, String condition) {
        temperatureLabel.setText("Temperature: " + temp);
        humidityLabel.setText("Humidity: " + humidity);
        windSpeedLabel.setText("Wind Speed: " + wind);
        conditionLabel.setText("Condition: " + condition);
    }
}
