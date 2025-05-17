package model;

public class Fahrenheit implements TemperatureUnit {
    @Override
    public double convert(double temperatureCelsius) {
        return (temperatureCelsius * 9 / 5) + 32;
    }

    @Override
    public String getSymbol() {
        return "°F";
    }
}
