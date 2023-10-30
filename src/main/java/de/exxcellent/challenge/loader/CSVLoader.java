package de.exxcellent.challenge.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * CSVLoader provides static methods to load CSV Files from various sources
 */
public class CSVLoader {

    /**
     * Loads an utf-8 encoded CSV file from a local file given by its filepath
     *
     * @param filePath The path to the csv file
     * @return A list of all lines contained in the file
     * @throws IOException if the file can't be read
     */
    public static List<String> loadCSVFromFile(String filePath) throws IOException {
        return CSVLoader.loadCSVFromFile(filePath, StandardCharsets.UTF_8);
    }

    /**
     * Loads a CSV file from a local file given by its filepath and encoding
     *
     * @param filePath The path to the csv file
     * @param encoding The encoding used in the csv file
     * @return A list of all lines contained in the file
     * @throws IOException if the file can't be read
     */
    public static List<String> loadCSVFromFile(String filePath, Charset encoding) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath, encoding));
        return reader.lines().toList();
    }
}
