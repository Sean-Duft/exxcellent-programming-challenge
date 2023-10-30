package de.exxcellent.challenge.data;

/**
 * Data class to represent a days weather data
 *
 * @param day The day the weather data belong to
 * @param maxTemperature The maximum temperature achieved
 * @param minTemperature The minimum temperature achieved
 * @param averageTemperature The average temperature across the entire day
 * @param averageDewPoint The average dew point across the entire day
 * @param oneHourTotalPrecipitation The maximum precipitation recorded in one hour
 * @param windDirectionDegrees The prevailing wind direction in degrees
 * @param averageWindSpeed The average wind speed
 * @param windDirectionDegreesPeak The wind direction of the strongest wind in degrees
 * @param maxWindSpeed The maximum wind speed recorded
 * @param skyCover The total sky cover
 * @param maxHumidity The maximum relative humidity
 * @param minHumidity The minimum relative humidity
 * @param averageSeaLevelPressure The average sea level pressure
 * @see <a href=https://www.ldeo.columbia.edu/~bhuber/jberrt/Data/jfkweather.pdf>Source for abbreviation meaning</a>
 */
public record WeatherData(int day, int maxTemperature, int minTemperature, int averageTemperature,
                          float averageDewPoint, int oneHourTotalPrecipitation, int windDirectionDegrees,
                          float averageWindSpeed, int windDirectionDegreesPeak, int maxWindSpeed, float skyCover,
                          int maxHumidity, int minHumidity, float averageSeaLevelPressure) {
}
