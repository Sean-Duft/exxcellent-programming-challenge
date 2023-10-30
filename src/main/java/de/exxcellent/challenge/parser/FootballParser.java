package de.exxcellent.challenge.parser;

import de.exxcellent.challenge.data.FootballData;
import de.exxcellent.challenge.data.WeatherData;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FootballParser extends DataParser<FootballData> {

    /**
     * Amount of fields in the WeatherData class
     */
    private final int dataFields = 8;

    /**
     * Creates a new instance of FootballData from the given data
     *
     * @param data      The data representing the object
     * @param delimiter The delimiter used to separate values in the data
     * @return a FootballData object representing the given data
     */
    @Override
    protected FootballData parseDataFromCSV(String data, String delimiter) {
        if (delimiter == null || delimiter.isBlank()) throw new IllegalArgumentException("No valid delimiter provided");
        if (data == null) throw new IllegalArgumentException("No valid line data provided");
        String[] dataEntries = this.splitLine(data, delimiter);
        if (dataEntries.length < dataFields) throw new IllegalArgumentException("Not enough data entries provided");

        try {
            String teamName = dataEntries[0];
            int gamesPlayed = Integer.valueOf(dataEntries[1]);
            int wins = Integer.valueOf(dataEntries[2]);
            int losses = Integer.valueOf(dataEntries[3]);
            int draws = Integer.valueOf(dataEntries[4]);
            int goalsScored = Integer.valueOf(dataEntries[5]);
            int goalsAllowed = Integer.valueOf(dataEntries[6]);
            int points = Integer.valueOf(dataEntries[7]);

            return new FootballData(teamName, gamesPlayed, wins, losses, draws, goalsScored, goalsAllowed, points);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid data type provided");
        }
    }
}
