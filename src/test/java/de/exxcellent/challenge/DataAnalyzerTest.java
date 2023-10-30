package de.exxcellent.challenge;

import de.exxcellent.challenge.analyzer.DataAnalyzer;
import de.exxcellent.challenge.data.WeatherData;
import de.exxcellent.challenge.loader.CSVLoader;
import de.exxcellent.challenge.parser.DataParser;
import de.exxcellent.challenge.parser.WeatherParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataAnalyzerTest {
    private final String weatherDataFilePath = "src/main/resources/de/exxcellent/challenge/weather.csv";

    @Test
    public void findMinTest() throws IOException {
        WeatherParser weatherParser = new WeatherParser();
        List<WeatherData> weatherData = weatherParser.parseCSV(CSVLoader.loadCSVFromFile(weatherDataFilePath), ",", true);
        DataAnalyzer<WeatherData> weatherAnalyzer = new DataAnalyzer<>();
        assertEquals(weatherData.size(), weatherAnalyzer.findSmallest(weatherData, data -> data.oneHourTotalPrecipitation()).size());
        WeatherData minTemp = weatherAnalyzer.findSmallest(weatherData, data->data.minTemperature()).get(0);
        weatherData.forEach(data -> assertTrue(minTemp.minTemperature() <= data.minTemperature()));
    }
}
