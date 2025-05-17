package service;

import model.CityWeatherData;
import model.WeatherDataset;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CsvWeatherDataService implements WeatherDataService {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public WeatherDataset loadWeatherData(String filePath) {
        WeatherDataset dataset = new WeatherDataset();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(filePath), "UTF-8"))) {

            String line;
            boolean headerSkipped = false;

            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true; // Skip the header
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length < 6) continue;

                // Remove BOM if present (fixes "Ä°zmir" to "İzmir")
                String cityName = parts[0].trim().replace("\uFEFF", "");
                LocalDate date = LocalDate.parse(parts[1].trim(), DATE_FORMAT);
                double temp = Double.parseDouble(parts[2].trim());
                double humidity = Double.parseDouble(parts[3].trim());
                double windSpeed = Double.parseDouble(parts[4].trim());
                String condition = parts[5].trim();

                CityWeatherData data = new CityWeatherData(cityName, date, temp, humidity, windSpeed, condition);
                dataset.addData(data);
            }

        } catch (Exception e) {
            System.err.println("Error reading weather data: " + e.getMessage());
            e.printStackTrace();
        }

        return dataset;
    }
}
