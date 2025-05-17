package model;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class StatisticsCalculator {

    public static String getCityWithHighestAverageTemperature(List<CityWeatherData> data) {
        return getCityWithExtremeAverage(data, Comparator.comparingDouble(Double::doubleValue), true, "temperature");
    }

    public static String getCityWithLowestAverageTemperature(List<CityWeatherData> data) {
        return getCityWithExtremeAverage(data, Comparator.comparingDouble(Double::doubleValue), false, "temperature");
    }

    public static String getCityWithLowestTemperatureInJanuary(List<CityWeatherData> data) {
        return data.stream()
                .filter(d -> d.getDate().getMonthValue() == 1)
                .min(Comparator.comparingDouble(CityWeatherData::getTemperatureCelsius))
                .map(CityWeatherData::getCityName)
                .orElse("N/A");
    }

    public static String getCityWithHighestAvgHumidityInMay(List<CityWeatherData> data) {
        return getCityWithExtremeAverage(data.stream()
                .filter(d -> d.getDate().getMonthValue() == 5)
                .toList(),
                Comparator.comparingDouble(Double::doubleValue), true, "humidity");
    }

    public static String getCityWithHighestAvgWindInApril(List<CityWeatherData> data) {
        return getCityWithExtremeAverage(data.stream()
                .filter(d -> d.getDate().getMonthValue() == 4)
                .toList(),
                Comparator.comparingDouble(Double::doubleValue), true, "wind");
    }

    
    private static String getCityWithExtremeAverage(List<CityWeatherData> data,
                                                    Comparator<Double> comparator,
                                                    boolean max,
                                                    String type) {
        Map<String, List<CityWeatherData>> grouped = data.stream()
                .collect(Collectors.groupingBy(CityWeatherData::getCityName));

        return grouped.entrySet().stream()
                .map(entry -> {
                    String city = entry.getKey();
                    List<CityWeatherData> cityData = entry.getValue();
                    double avg = switch (type) {
                        case "temperature" -> cityData.stream()
                                .mapToDouble(CityWeatherData::getTemperatureCelsius).average().orElse(Double.NaN);
                        case "humidity" -> cityData.stream()
                                .mapToDouble(CityWeatherData::getHumidity).average().orElse(Double.NaN);
                        case "wind" -> cityData.stream()
                                .mapToDouble(CityWeatherData::getWindSpeed).average().orElse(Double.NaN);
                        default -> Double.NaN;
                    };
                    return new AbstractMap.SimpleEntry<>(city, avg);
                })
                .filter(entry -> !Double.isNaN(entry.getValue()))
                .max(max ? Map.Entry.comparingByValue() : Map.Entry.comparingByValue(comparator.reversed()))
                .map(Map.Entry::getKey)
                .orElse("N/A");
    }
}
