package service;

import model.WeatherDataset;

public interface WeatherDataService {
    WeatherDataset loadWeatherData(String filePath);
}
