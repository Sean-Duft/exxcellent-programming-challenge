package de.exxcellent.challenge.parser;

import de.exxcellent.challenge.data.WeatherData;

public class WeatherParser extends DataParser<WeatherData> {

    /**
     * Amount of fields in the WeatherData class
     */
    private final int dataFields = 14;

    /**
     * Creates a new instance of WeaterData from the given data
     *
     * @param data      The data representing the object
     * @param delimiter The delimiter used to separate values in the data
     * @return a WeatherData object representing the given data
     */
    @Override
    protected WeatherData parseDataFromCSV(String data, String delimiter) {
        if (delimiter == null || delimiter.isBlank()) throw new IllegalArgumentException("No valid delimiter provided");
        if (data == null) throw new IllegalArgumentException("No valid line data provided");
        String[] dataEntries = this.splitLine(data, delimiter);
        if (dataEntries.length < dataFields) throw new IllegalArgumentException("Not enough data entries provided");

        try {
            int day = Integer.valueOf(dataEntries[0]);
            int maxTemperature = Integer.valueOf(dataEntries[1]);
            int minTemperature = Integer.valueOf(dataEntries[2]);
            int averageTemperature = Integer.valueOf(dataEntries[3]);
            float averageDewPoint = Float.valueOf(dataEntries[4]);
            int oneHourTotalPrecipitation = Integer.valueOf(dataEntries[5]);
            int windDirectionDegrees = Integer.valueOf(dataEntries[6]);
            float averageWindSpeed = Float.valueOf(dataEntries[7]);
            int windDirectionDegreesPeakDegrees = Integer.valueOf(dataEntries[8]);
            int maxWindSpeed = Integer.valueOf(dataEntries[9]);
            float skyCover = Float.valueOf(dataEntries[10]);
            int maxHumidity = Integer.valueOf(dataEntries[11]);
            int minHumidity = Integer.valueOf(dataEntries[12]);
            float averageSeaLevelPressure = Float.valueOf(dataEntries[13]);

            return new WeatherData(day, maxTemperature, minTemperature, averageTemperature, averageDewPoint,
                    oneHourTotalPrecipitation, windDirectionDegrees, averageWindSpeed, windDirectionDegreesPeakDegrees,
                    maxWindSpeed, skyCover, maxHumidity, minHumidity, averageSeaLevelPressure);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid data type provided");
        }
    }
}
