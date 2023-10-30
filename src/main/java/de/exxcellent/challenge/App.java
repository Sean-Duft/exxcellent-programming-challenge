package de.exxcellent.challenge;

import de.exxcellent.challenge.analyzer.DataAnalyzer;
import de.exxcellent.challenge.data.FootballData;
import de.exxcellent.challenge.data.WeatherData;
import de.exxcellent.challenge.loader.CSVLoader;
import de.exxcellent.challenge.parser.FootballParser;
import de.exxcellent.challenge.parser.WeatherParser;

import java.io.IOException;
import java.util.List;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     *
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {
        App.analyzeWeatherData();
        App.analyzeFootballData();
    }

    private static void analyzeWeatherData() {
        String weatherDataFilePath = "src/main/resources/de/exxcellent/challenge/weather.csv";

        try {
            List<String> weatherDataCSV = CSVLoader.loadCSVFromFile(weatherDataFilePath);
            if (weatherDataCSV == null || weatherDataCSV.isEmpty()) {
                System.out.println("No weather data could be read");
                return;
            }
            WeatherParser weatherParser = new WeatherParser();
            List<WeatherData> weatherData = weatherParser.parseCSV(weatherDataCSV, ",", true);
            if (weatherData == null || weatherData.isEmpty()) {
                System.out.println("Weather data could not be parsed");
                return;
            }
            DataAnalyzer<WeatherData> weatherAnalyzer = new DataAnalyzer<>();
            List<WeatherData> smallestWeatherDifference = weatherAnalyzer.findSmallest(weatherData,
                    data -> Math.abs(data.maxTemperature() - data.minTemperature()));
            if (smallestWeatherDifference.isEmpty()) {
                System.out.println("Somehow couldn't find a single matching weather data entry");
                return;
            }
            int dayWithSmallestTempSpread = smallestWeatherDifference.get(0).day();
            System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);
        } catch (IOException e) {
            System.err.println("Unable to load weather data");
        }
    }

    public static void analyzeFootballData() {
        String footballDataFilePath = "src/main/resources/de/exxcellent/challenge/football.csv";

        try {
            List<String> footballDataCSV = CSVLoader.loadCSVFromFile(footballDataFilePath);
            if (footballDataCSV == null || footballDataCSV.isEmpty()) {
                System.out.println("No football data could be read");
                return;
            }
            FootballParser footballParser = new FootballParser();
            List<FootballData> footballData = footballParser.parseCSV(footballDataCSV, ",", true);
            if (footballData == null || footballData.isEmpty()) {
                System.out.println("football data could not be parsed");
                return;
            }
            DataAnalyzer<FootballData> footballAnalyzer = new DataAnalyzer<>();
            List<FootballData> smallestFootballDifference = footballAnalyzer.findSmallest(footballData,
                    data -> Math.abs(data.goalsAllowed() - data.goalsScored()));
            if (smallestFootballDifference.isEmpty()) {
                System.out.println("Somehow couldn't find a single matching football data entry");
                return;
            }
            String teamWithSmallestGoalSpread = smallestFootballDifference.get(0).teamName();
            System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
        } catch (IOException e) {
            System.err.println("Unable to load football data");
        }
    }
}
