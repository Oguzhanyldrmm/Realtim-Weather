package model;

import java.time.LocalDate;

public class CityWeatherData {
    private String cityName;
    private LocalDate date;
    private double temperatureCelsius;
    private double humidity;
    private double windSpeed;
    private String condition; // NEW FIELD

    public CityWeatherData(String cityName, LocalDate date, double temperatureCelsius,
                           double humidity, double windSpeed, String condition) {
        this.cityName = cityName;
        this.date = date;
        this.temperatureCelsius = temperatureCelsius;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.condition = condition;
    }

    public String getCityName() {
        return cityName;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getCondition() {
        return condition;
    }
}
