package model;

public interface TemperatureUnit {
    double convert(double temperatureCelsius);
    String getSymbol();  
}
