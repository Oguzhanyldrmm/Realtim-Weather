import controller.WeatherController;
import model.WeatherDataset;
import service.CsvWeatherDataService;
import service.WeatherDataService;
import view.MainFrame;

public class Main {
    public static void main(String[] args) {
        WeatherDataService service = new CsvWeatherDataService();
        WeatherDataset dataset = service.loadWeatherData("weather_data.csv");

        javax.swing.SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            new WeatherController(dataset, frame);
        });
    }
}
