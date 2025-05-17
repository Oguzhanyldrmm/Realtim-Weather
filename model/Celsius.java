package model;

public class Celsius implements TemperatureUnit {
    @Override
    public double convert(double temperatureCelsius) {
        return temperatureCelsius;
    }

    @Override
    public String getSymbol() {
        return "°C";
    }
}
