package de.exxcellent.challenge.analyzer;

import java.util.Collections;
import java.util.List;

/**
 * Class to perform analysis on various data
 * @param <T> the dataclass to analyze
 */
public class DataAnalyzer<T> {

    /**
     * Finds the smallest element from the given list when reduces through valueSupplier
     * @param elements The list of data
     * @param valueSupplier Function that reduces an element to an int value
     * @return A list of all elements with the smallest int value
     */
    public List<T> findSmallest(List<T> elements, ValueSupplier<T> valueSupplier){
        List<Integer> values = elements.stream().map(valueSupplier::supplyIntValue).toList();
        int minValue = Collections.min(values);
        return elements.stream().filter(element -> valueSupplier.supplyIntValue(element) == minValue).toList();
    }

    /**
     * Interface providing methods to extract values out of the dataclass T
     * @param <T> the data class that should be analyzed
     */
    public interface ValueSupplier<T> {
        int supplyIntValue(T element);
    }
}
