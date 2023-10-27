package de.exxcellent.challenge.loader;

import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * CSVLoader provides static methods to load CSV Files from various sources
 */
public class CSVLoader {

    /**
     * Loads a utf-8 encoded CSV file from a local file given by its filepath
     * @param filePath The path to the csv file
     * @return The string contained in the file
     */
    public static String loadCSVFromFile(String filePath) {
        return CSVLoader.loadCSVFromFile(filePath, StandardCharsets.UTF_8);
    }

    /**
     * Loads a utf-8 encoded CSV file from a local file given by its filepath
     * @param filePath The path to the csv file
     * @param encoding The encoding used in the csv file
     * @return The string contained in the file
     */
    public static String loadCSVFromFile(String filePath, Charset encoding) {
        return null;
    }
}
