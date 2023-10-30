package de.exxcellent.challenge;

import de.exxcellent.challenge.data.FootballData;
import de.exxcellent.challenge.data.WeatherData;
import de.exxcellent.challenge.loader.CSVLoader;
import de.exxcellent.challenge.parser.FootballParser;
import de.exxcellent.challenge.parser.WeatherParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataParserTest {

    private final String footballDataFilePath = "src/main/resources/de/exxcellent/challenge/football.csv";
    private final String weatherDataFilePath = "src/main/resources/de/exxcellent/challenge/weather.csv";
    private List<String> footballDataRaw;
    private List<String> weatherDataRaw;
    private WeatherParser weatherParser = new WeatherParser();
    private FootballParser footballParser = new FootballParser();

    {
        try {
            footballDataRaw = CSVLoader.loadCSVFromFile(footballDataFilePath);
            weatherDataRaw = CSVLoader.loadCSVFromFile(weatherDataFilePath);
        } catch (IOException e) {
            System.err.println("Failed to create test case for DataParser. Perhaps, CSVLoader is not working");
            e.printStackTrace();
        }
    }

    @Test
    public void testFootballParser() {
        List<FootballData> footballData = footballParser.parseCSV(footballDataRaw, ",", true);
        //make sure all the data sets were loaded
        assertEquals(20, footballData.size(), "Failed to load all datasets");

        FootballData first = footballData.get(0);
        assertEquals("Arsenal", first.teamName(), "First football data has wrong name");
        assertEquals(38, first.gamesPlayed(), "First football data has wrong amount of matches");
        assertEquals(26, first.wins(), "First football data has wrong amount of wins");
        assertEquals(9, first.losses(), "First football data has wrong amount of losses");
        assertEquals(3, first.draws(), "First football data has wrong amount of draws");
        assertEquals(79, first.goalsScored(), "First football data has wrong amount of goals scored");
        assertEquals(36, first.goalsAllowed(), "First football data has wrong amount of goals conceded");
        assertEquals(87, first.points(), "First football data has wrong amount of points");

        FootballData last = footballData.get(footballData.size() - 1);
        assertEquals("Leicester", last.teamName(), "Last football data has wrong name");
        assertEquals(38, last.gamesPlayed(), "Last football data has wrong amount of matches");
        assertEquals(5, last.wins(), "Last football data has wrong amount of wins");
        assertEquals(13, last.losses(), "Last football data has wrong amount of losses");
        assertEquals(20, last.draws(), "Last football data has wrong amount of draws");
        assertEquals(30, last.goalsScored(), "Last football data has wrong amount of goals scored");
        assertEquals(64, last.goalsAllowed(), "Last football data has wrong amount of goals conceded");
        assertEquals(28, last.points(), "Last football data has wrong amount of points");
    }

    @Test
    public void testWeatherParser() {
        List<WeatherData> weatherData = weatherParser.parseCSV(weatherDataRaw, ",", true);
        //make sure all the data sets were loaded
        assertEquals(30, weatherData.size(), "Failed to load all datasets");

        WeatherData first = weatherData.get(0);
        assertEquals(1, first.day(), "First weather data has wrong day");
        assertEquals(88, first.maxTemperature(), "First weather data has wrong max temperature");
        assertEquals(59, first.minTemperature(), "First weather data has wrong min temperature");
        assertEquals(74, first.averageTemperature(), "First weather data has wrong average temperature");
        assertEquals(53.8, first.averageDewPoint(), 0.01, "First weather data has wrong average dew point");
        assertEquals(0, first.oneHourTotalPrecipitation(), "First weather data has wrong total precipitation");
        assertEquals(280, first.windDirectionDegrees(), "First weather data has wrong wind direction");
        assertEquals(9.6, first.averageWindSpeed(), 0.01, "First weather data has wrong average wind speed");
        assertEquals(17, first.maxWindSpeed(), "First weather data has wrong max wind speed");
        assertEquals(270, first.windDirectionDegreesPeak(), "First weather data has wrong peak wind direction");
        assertEquals(1.6, first.skyCover(), 0.01, "First weather data has wrong sky cover");
        assertEquals(23, first.minHumidity(), "First weather data has wrong min humidity");
        assertEquals(93, first.maxHumidity(), "First weather data has wrong max humidity");
        assertEquals(1004.5, first.averageSeaLevelPressure(), 0.01, "First weather data has wrong sea level pressure");

        WeatherData last = weatherData.get(weatherData.size() - 1);
        assertEquals(30, last.day(), "Last weather data has wrong day");
        assertEquals(90, last.maxTemperature(), "Last weather data has wrong max temperature");
        assertEquals(45, last.minTemperature(), "Last weather data has wrong min temperature");
        assertEquals(68, last.averageTemperature(), "Last weather data has wrong average temperature");
        assertEquals(63.6, last.averageDewPoint(), 0.01, "Last weather data has wrong average dew point");
        assertEquals(0, last.oneHourTotalPrecipitation(), "Last weather data has wrong total precipitation");
        assertEquals(240, last.windDirectionDegrees(), "Last weather data has wrong wind direction");
        assertEquals(6, last.averageWindSpeed(), 0.01, "Last weather data has wrong average wind speed");
        assertEquals(17, last.maxWindSpeed(), "Last weather data has wrong max wind speed");
        assertEquals(220, last.windDirectionDegreesPeak(), "Last weather data has wrong peak wind direction");
        assertEquals(4.8, last.skyCover(), 0.01, "Last weather data has wrong sky cover");
        assertEquals(41, last.minHumidity(), "Last weather data has wrong min humidity");
        assertEquals(200, last.maxHumidity(), "Last weather data has wrong max humidity");
        assertEquals(1022.7, last.averageSeaLevelPressure(), 0.01, "Last weather data has wrong sea level pressure");
    }

    @Test
    public void testRemoveHeader() {
        List<String> weatherRaw = Arrays.asList("0,1,2,3,4,5,6,7,8,9,10,11,12,13", "14,15,16,17,18,19,20,21,22,23,24,25,26,27");
        List<WeatherData> weatherDataWithHeader = weatherParser.parseCSV(weatherRaw, ",", true);
        List<WeatherData> weatherDataNoHeader = weatherParser.parseCSV(weatherRaw, ",", false);
        assertEquals(1, weatherDataWithHeader.size(), "Only expected one entry after removing header");
        assertEquals(2, weatherDataNoHeader.size(), "Expected two entries when keeping first line");
        //make sure the correct line gets removed
        assertEquals(14, weatherDataWithHeader.get(0).day(), "Wrong entry was removed with header");
        assertEquals(0, weatherDataNoHeader.get(0).day(), "Wrong entry was removed with header");

        List<String> footballRaw = Arrays.asList("Club 1,0,0,0,0,0,0,0", "Club 2,0,0,0,0,0,0,0");
        List<FootballData> footballDataWithHeader = footballParser.parseCSV(footballRaw, ",", true);
        List<FootballData> footballDataNoHeader = footballParser.parseCSV(footballRaw, ",", false);
        assertEquals(1, footballDataWithHeader.size(), "Only expected one entry after removing header");
        assertEquals(2, footballDataNoHeader.size(), "Expected two entries when keeping first line");
        //make sure the correct line gets removed
        assertEquals("Club 2", footballDataWithHeader.get(0).teamName(), "Wrong entry was removed with header");
        assertEquals("Club 1", footballDataNoHeader.get(0).teamName(), "Wrong entry was removed with header");
    }

    @Test
    public void testInvalidData() {
        //test no data
        assertThrows(IllegalArgumentException.class, () -> weatherParser.parseCSV(null, ",", true));
        assertThrows(IllegalArgumentException.class, () -> footballParser.parseCSV(null, ",", true));

        //test too little data entries
        List<String> weatherRawEmpty = Arrays.asList("", "");
        List<String> weatherRawTooShort = Arrays.asList("0,1,2,3,4,5,6,7,8,9,10,11,12", "14,15,16,17,18,19,20,21,22,23,24,25,26");
        List<String> weatherRawTooLong = Arrays.asList("0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16", "14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30");
        List<String> weatherRawMissingValue = Arrays.asList("0,1,2,3,4,5,6,,8,9,10,11,12,13", "14,15,16,17,18,19,20,21,22,23,24,25,26,27");
        List<String> weatherRawWrongDataType = Arrays.asList("0,1,2,3,4,5,6,7,8,9,10,11a,12,13", "14,15,16,17,18,19,20,21,22,23,24,25,26,27");
        assertThrows(IllegalArgumentException.class, () -> weatherParser.parseCSV(weatherRawEmpty, ",", false));
        assertThrows(IllegalArgumentException.class, () -> weatherParser.parseCSV(weatherRawTooShort, ",", false));
        assertThrows(IllegalArgumentException.class, () -> weatherParser.parseCSV(weatherRawMissingValue, ",", false));
        assertThrows(IllegalArgumentException.class, () -> weatherParser.parseCSV(weatherRawWrongDataType, ",", false));
        assertDoesNotThrow(() -> weatherParser.parseCSV(weatherRawTooLong, ",", false));

        List<String> footballRawEmpty = Arrays.asList("", "");
        List<String> footballRawTooShort = Arrays.asList("Club 1,0,0,0,0,0", "Club 2,0,0,0,0,0");
        List<String> footballRawTooLong = Arrays.asList("Club 1,0,0,0,0,0,0,0,7,7,7", "Club 2,0,0,0,0,0,0,0,8,8");
        List<String> footballRawMissingValue = Arrays.asList("Club 1,0,0,0,,0,0,0", "Club 2,0,0,0,0,0,0,0");
        List<String> footballRawWrongDataType = Arrays.asList("Club 1,0,0,U,0,0,0,0", "Club 2,0,0,0,0,0,0,0");
        assertThrows(IllegalArgumentException.class, () -> footballParser.parseCSV(footballRawEmpty, ",", false));
        assertThrows(IllegalArgumentException.class, () -> footballParser.parseCSV(footballRawTooShort, ",", false));
        assertThrows(IllegalArgumentException.class, () -> footballParser.parseCSV(footballRawMissingValue, ",", false));
        assertThrows(IllegalArgumentException.class, () -> footballParser.parseCSV(footballRawWrongDataType, ",", false));
        assertDoesNotThrow(() -> footballParser.parseCSV(footballRawTooLong, ",", false));
    }

    @Test
    public void testDelimiter() {
        assertThrows(IllegalArgumentException.class, () -> weatherParser.parseCSV(weatherDataRaw, null, true));
        assertThrows(IllegalArgumentException.class, () -> footballParser.parseCSV(footballDataRaw, null, true));

        assertThrows(IllegalArgumentException.class, () -> weatherParser.parseCSV(weatherDataRaw, "", true));
        assertThrows(IllegalArgumentException.class, () -> footballParser.parseCSV(footballDataRaw, "", true));

        List<String> footballDataOtherDelimiter = Arrays.asList("Club 1;0;0;0;0;0;0;0", "Club 2;0;0;0;0;0;0;0");
        List<String> footballDataMoreThanOneCharDelimiter = Arrays.asList("Club 1tab0tab0tab0tab0tab0tab0tab0", "Club 2tab0tab0tab0tab0tab0tab0tab0");

        List<FootballData> footballDataSemicolon = footballParser.parseCSV(footballDataOtherDelimiter, ";", false);
        List<FootballData> footballDataTab = footballParser.parseCSV(footballDataMoreThanOneCharDelimiter, "tab", false);

        assertEquals(2, footballDataSemicolon.size(), "Not all data entries were parsed");
        assertEquals("Club 1", footballDataSemicolon.get(0).teamName(), "Wrong entry after parsing with different delimiter");
        assertEquals(2, footballDataTab.size(), "Not all data entries were parsed");
        assertEquals("Club 1", footballDataTab.get(0).teamName(), "Wrong entry after parsing with different delimiter");
        assertEquals(footballDataTab.get(0), footballDataSemicolon.get(0), "parsed Elements did not match");
        assertEquals(footballDataTab.get(1), footballDataSemicolon.get(1), "parsed Elements did not match");
        assertThrows(IllegalArgumentException.class, () -> footballParser.parseCSV(footballDataOtherDelimiter, ",", false));

        List<String> weatherDataOtherDelimiter = Arrays.asList("0;1;2;3;4;5;6;7;8;9;10;11;12;13", "14;15;16;17;18;19;20;21;22;23;24;25;26;27");
        List<String> weatherDataMoreThanOneCharDelimiter = Arrays.asList("0tab1tab2tab3tab4tab5tab6tab7tab8tab9tab10tab11tab12tab13", "14tab15tab16tab17tab18tab19tab20tab21tab22tab23tab24tab25tab26tab27");

        List<WeatherData> weatherDataSemicolon = weatherParser.parseCSV(weatherDataOtherDelimiter, ";", false);
        List<WeatherData> weatherDataTab = weatherParser.parseCSV(weatherDataMoreThanOneCharDelimiter, "tab", false);

        assertEquals(2, weatherDataSemicolon.size(), "Not all data entries were parsed");
        assertEquals(0, weatherDataSemicolon.get(0).day(), "Wrong entry after parsing with different delimiter");
        assertEquals(2, weatherDataTab.size(), "Not all data entries were parsed");
        assertEquals(0, weatherDataTab.get(0).day(), "Wrong entry after parsing with different delimiter");
        assertEquals(weatherDataTab.get(0), weatherDataSemicolon.get(0), "parsed Elements did not match");
        assertEquals(weatherDataTab.get(1), weatherDataSemicolon.get(1), "parsed Elements did not match");
        assertThrows(IllegalArgumentException.class, () -> weatherParser.parseCSV(weatherDataOtherDelimiter, ",", false));


    }
}
