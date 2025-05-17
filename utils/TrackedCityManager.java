package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TrackedCityManager {

    private final String filePath;

    public TrackedCityManager(String filePath) {
        this.filePath = filePath;
    }

    public List<String> getTrackedCities() {
        try {
            return Files.readAllLines(Path.of(filePath))
                        .stream()
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .toList();
        } catch (IOException e) {
            System.err.println("Couldn't load tracked cities: " + e.getMessage());
            return List.of();
        }
    }
}
