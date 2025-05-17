package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CitySelectionPanel citySelectionPanel;
    private WeatherDisplayPanel weatherDisplayPanel;
    private StatisticsPanel statisticsPanel;
    private UnitSelectorPanel unitSelectorPanel;
    private TrackedCitiesPanel trackedCitiesPanel;

    public MainFrame() {
        super("Realtime Weather 🌤");

        // Bileşenleri oluştur
        citySelectionPanel = new CitySelectionPanel();
        weatherDisplayPanel = new WeatherDisplayPanel();
        statisticsPanel = new StatisticsPanel();
        unitSelectorPanel = new UnitSelectorPanel();
        trackedCitiesPanel = new TrackedCitiesPanel(); // Oluştur

        // Yerleşim düzeni
        setLayout(new BorderLayout());

        // Panel yerleşimi
        add(unitSelectorPanel, BorderLayout.NORTH);
        add(citySelectionPanel, BorderLayout.WEST);
        add(weatherDisplayPanel, BorderLayout.CENTER);
        add(statisticsPanel, BorderLayout.SOUTH);
        add(trackedCitiesPanel, BorderLayout.EAST);   // GUI’ye ekle

        // Pencere ayarları
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null); // Ortala
        setVisible(true);
    }

    // Getter metodları controller ile iletişim için
    public CitySelectionPanel getCitySelectionPanel() {
        return citySelectionPanel;
    }

    public WeatherDisplayPanel getWeatherDisplayPanel() {
        return weatherDisplayPanel;
    }

    public StatisticsPanel getStatisticsPanel() {
        return statisticsPanel;
    }

    public UnitSelectorPanel getUnitSelectorPanel() {
        return unitSelectorPanel;
    }

    public TrackedCitiesPanel getTrackedCitiesPanel() {
        return trackedCitiesPanel;
    }
}
