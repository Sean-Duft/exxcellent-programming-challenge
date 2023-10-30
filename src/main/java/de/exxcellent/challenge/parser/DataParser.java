package de.exxcellent.challenge.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Provides methods to parse String data into data representations
 *
 * @param <T> the class the data is parsed to
 */
public abstract class DataParser<T> {

    /**
     * Turns a list of Strings with values separated by a delimiter into a list of instances of the class T
     *
     * @param lines     the lines representing the individual data elements
     * @param delimiter the delimiter used to separate individual fields
     * @param hasHeader if lines starts with a header row that is not a data entry
     * @return A list of instances of T representing the data
     */
    public List<T> parseCSV(List<String> lines, String delimiter, boolean hasHeader) {
        if(lines == null) throw new IllegalArgumentException("CSV data was null");
        List<String> data = discardCSVHeader(lines, hasHeader);
        return data.stream()
                .map(dataEntry -> parseDataFromCSV(dataEntry, delimiter))
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Creates a new instance of T from the given data
     *
     * @param data      The data representing the object
     * @param delimiter The delimiter used to separate values in the data
     * @return A new instance of the class T from the given values
     */
    protected abstract T parseDataFromCSV(String data, String delimiter);

    /**
     * Removes the first header-line of the data if necessary
     *
     * @param lines     the data
     * @param hasHeader if the data has a header row
     * @return the data without a column header
     */
    protected List<String> discardCSVHeader(List<String> lines, boolean hasHeader) {
        if (!hasHeader || lines == null || lines.size() == 0) return lines;
        return lines.subList(1, lines.size());
    }

    /**
     * Splits a line into multiple parts given the delimiter
     *
     * @param line      The string to be split
     * @param delimiter The delimiter to split at
     * @return A string array containing the individual parts
     */
    protected String[] splitLine(String line, String delimiter) {
        if (line == null) return new String[0];
        return line.split(delimiter);
    }
}
