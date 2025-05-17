package controller;

import model.*;
import service.WeatherDataService;
import view.*;
import util.TrackedCityManager;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class WeatherController {

    private WeatherDataset dataset;
    private TemperatureUnit temperatureUnit;
    private MainFrame mainFrame;

    public WeatherController(WeatherDataset dataset, MainFrame mainFrame) {
        this.dataset = dataset;
        this.mainFrame = mainFrame;
        this.temperatureUnit = new Celsius(); // default

        init();
    }

    private void init() {
        // 1. Şehirleri GUI'ye yükle
        loadCitiesToComboBox();

        // 2. Buton tıklanınca işlem yap
        mainFrame.getCitySelectionPanel().getShowButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSelectedWeather();
            }
        });

        // 3. Sıcaklık birimi seçimi değiştiğinde güncelle
        // (Gelişmiş versiyonda Observer ile yapılabilir)
        Timer timer = new Timer(500, e -> updateTemperatureUnit());
        timer.start();

        // 4. Başlangıçta istatistikleri göster
        showStatistics();
        showTrackedCities(); // tracked cities'i göster

    }

    private void loadCitiesToComboBox() {
        List<String> cities = dataset.getAllData().stream()
                .map(CityWeatherData::getCityName)
                .distinct()
                .sorted()
                .toList();

        JComboBox<String> cityBox = mainFrame.getCitySelectionPanel().getCityComboBox();
        for (String city : cities) {
            cityBox.addItem(city);
        }
    }

    private void showSelectedWeather() {
        String selectedCity = (String) mainFrame.getCitySelectionPanel().getCityComboBox().getSelectedItem();
        Date selectedDate = (Date) mainFrame.getCitySelectionPanel().getDateSpinner().getValue();
        LocalDate localDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        List<CityWeatherData> dataList = dataset.getAllData();

        CityWeatherData match = dataList.stream()
                .filter(d -> d.getCityName().equalsIgnoreCase(selectedCity)
                        && d.getDate().equals(localDate))
                .findFirst()
                .orElse(null);

        if (match != null) {
            String temp = String.format("%.1f %s", temperatureUnit.convert(match.getTemperatureCelsius()), temperatureUnit.getSymbol());
            String humidity = match.getHumidity() + " %";
            String wind = match.getWindSpeed() + " km/h";
            String condition = match.getCondition();

            mainFrame.getWeatherDisplayPanel().updateWeatherInfo(temp, humidity, wind, condition);
        } else {
            JOptionPane.showMessageDialog(mainFrame, "No data found for selected city and date.");
        }
    }

    private void updateTemperatureUnit() {
        if (mainFrame.getUnitSelectorPanel().isCelsiusSelected()) {
            temperatureUnit = new Celsius();
        } else if (mainFrame.getUnitSelectorPanel().isFahrenheitSelected()) {
            temperatureUnit = new Fahrenheit();
        }
    }

    private void showStatistics() {
        List<CityWeatherData> data = dataset.getAllData();

        StringBuilder sb = new StringBuilder();
        sb.append("1. The city with the highest average temperature (Jan–May 2025): ")
                .append(StatisticsCalculator.getCityWithHighestAverageTemperature(data)).append("\n");
        sb.append("2. The city with the lowest average temperature (Jan–May 2025): ")
                .append(StatisticsCalculator.getCityWithLowestAverageTemperature(data)).append("\n");
        sb.append("3. The city with the lowest temperature in January 2025: ")
                .append(StatisticsCalculator.getCityWithLowestTemperatureInJanuary(data)).append("\n");
        sb.append("4. The city with the highest average humidity in May 2025: ")
                .append(StatisticsCalculator.getCityWithHighestAvgHumidityInMay(data)).append("\n");
        sb.append("5. The city with the highest average wind speed in April 2025: ")
                .append(StatisticsCalculator.getCityWithHighestAvgWindInApril(data)).append("\n");

        mainFrame.getStatisticsPanel().updateStatistics(sb.toString());
    }

    private void showTrackedCities() {
        TrackedCityManager manager = new TrackedCityManager("tracked_cities.txt");
        List<String> trackedCities = manager.getTrackedCities();
    
        StringBuilder sb = new StringBuilder();
        LocalDate today = LocalDate.now();
    
        for (String city : trackedCities) {
            CityWeatherData match = dataset.getAllData().stream()
                    .filter(d -> d.getCityName().equalsIgnoreCase(city)
                            && d.getDate().equals(today))
                    .findFirst()
                    .orElse(null);
    
            if (match != null) {
                sb.append(city).append(" (").append(today).append(")\n");
                sb.append("Temp: ").append(String.format("%.1f", temperatureUnit.convert(match.getTemperatureCelsius())))
                  .append(" ").append(temperatureUnit.getSymbol()).append("\n");
                sb.append("Humidity: ").append(match.getHumidity()).append(" %\n");
                sb.append("Wind: ").append(match.getWindSpeed()).append(" km/h\n");
                sb.append("Condition: ").append(match.getCondition()).append("\n\n");
            } else {
                sb.append(city).append(" → No data for today.\n\n");
            }
        }
    
        mainFrame.getTrackedCitiesPanel().updateTrackedWeather(sb.toString());
    }
    
}
