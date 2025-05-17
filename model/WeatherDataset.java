package model;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataset {
    private List<CityWeatherData> allData = new ArrayList<>();

    public void addData(CityWeatherData data) {
        allData.add(data);
    }

    public List<CityWeatherData> getAllData() {
        return allData;
    }

    public List<CityWeatherData> getCityData(String cityName) {
        return allData.stream()
            .filter(d -> d.getCityName().equalsIgnoreCase(cityName))
            .toList();
    }
}
