package de.exxcellent.challenge;

import de.exxcellent.challenge.loader.CSVLoader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CSVLoaderTest {

    private final String footballDataFilePath = "src/main/resources/de/exxcellent/challenge/football.csv";
    private final String weatherDataFilePath = "src/main/resources/de/exxcellent/challenge/weather.csv";

    @Test
    public void testFileLoading() throws IOException {
        //throw IOException on invalid files
        assertThrows(IOException.class, () -> CSVLoader.loadCSVFromFile("thisFileDoesNotExist.csv"), "Expected IOException due to file path not pointing to a valid file");
        //throw IOException when trying to read a folder
        assertThrows(IOException.class, () -> CSVLoader.loadCSVFromFile("src"), "Expected IOException when file path points to a folder");
        //throw no IOException on valid files
        assertDoesNotThrow(() -> CSVLoader.loadCSVFromFile(footballDataFilePath), "Tried to read valid file but an exception was thrown");
        assertDoesNotThrow(() -> CSVLoader.loadCSVFromFile(weatherDataFilePath), "Tried to read valid file but an exception was thrown");

        List<String> footballLines = CSVLoader.loadCSVFromFile(footballDataFilePath);
        List<String> weatherLines = CSVLoader.loadCSVFromFile(weatherDataFilePath);

        //ensure all lines are read
        assertEquals(31, weatherLines.size(), "Did not read all lines of weather data");
        assertEquals(21, footballLines.size(), "Did not read all lines of football data");

        //ensures the lines match
        assertEquals(footballLines.get(0), "Team,Games,Wins,Losses,Draws,Goals,Goals Allowed,Points", "Football header does not match");
        assertEquals(footballLines.get(1), "Arsenal,38,26,9,3,79,36,87", "First football data does not match");
        assertEquals(footballLines.get(20), "Leicester,38,5,13,20,30,64,28", "Last football data does not match");

        assertEquals(weatherLines.get(0), "Day,MxT,MnT,AvT,AvDP,1HrP TPcpn,PDir,AvSp,Dir,MxS,SkyC,MxR,Mn,R AvSLP", "Weather header does not match");
        assertEquals(weatherLines.get(1), "1,88,59,74,53.8,0,280,9.6,270,17,1.6,93,23,1004.5", "First weather data does not match");
        assertEquals(weatherLines.get(30), "30,90,45,68,63.6,0,240,6,220,17,4.8,200,41,1022.7", "Last weather data does not match");
    }
}
