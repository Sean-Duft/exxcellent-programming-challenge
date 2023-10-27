package de.exxcellent.challenge.loader;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CSVLoader provides static methods to load CSV Files from various sources
 */
public class CSVLoader {

    /**
     * Loads a utf-8 encoded CSV file from a local file given by its filepath
     *
     * @param filePath The path to the csv file
     * @return A list of all lines contained in the file
     * @throws IOException if the file can't be read
     */
    public static List<String> loadCSVFromFile(String filePath) throws IOException {
        return CSVLoader.loadCSVFromFile(filePath, StandardCharsets.UTF_8);
    }

    /**
     * Loads a utf-8 encoded CSV file from a local file given by its filepath
     *
     * @param filePath The path to the csv file
     * @param encoding The encoding used in the csv file
     * @return A list of all lines contained in the file
     * @throws IOException if the file can't be read
     */
    public static List<String> loadCSVFromFile(String filePath, Charset encoding) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath, encoding));
        return reader.lines().collect(Collectors.toCollection(ArrayList::new));
    }
}
