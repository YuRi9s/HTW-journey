import java.util.*;

/**
 * Consumes integers by calculating their cross totals and storing timestamps of
 * the calculations.
 */
public class Consumer {
    private Map<Integer, List<Long>> crossTotalTimestamps = new HashMap<>();

    /**
     * Consumes a number by calculating its cross total and storing the timestamp of
     * the calculation.
     *
     * @param number the number to consume
     */
    public void consume(int number) {
        int crossTotal = calculateCrossTotal(number);
        long timestamp = System.currentTimeMillis();
        crossTotalTimestamps.computeIfAbsent(crossTotal, k -> new ArrayList<>()).add(timestamp);
    }

    /**
     * Calculates the cross total of a number.
     *
     * @param number the number
     * @return the cross total
     */
    public int calculateCrossTotal(int number) {
        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    /**
     * Gets the number of different cross totals calculated.
     *
     * @return the number of different cross totals
     */
    public int numberOfDifferentResults() {
        return crossTotalTimestamps.size();
    }

    /**
     * Gets the number of occurrences of a specific cross total.
     *
     * @param crossTotal the cross total
     * @return the number of occurrences
     */
    public int numberOfOccurrences(int crossTotal) {
        return crossTotalTimestamps.getOrDefault(crossTotal, Collections.emptyList()).size();
    }

    /**
     * Gets cross totals in ascending order.
     *
     * @return a collection of cross totals in ascending order
     */
    public Collection<Integer> getCrossTotalsAscending() {
        List<Integer> crossTotals = new ArrayList<>(crossTotalTimestamps.keySet());
        Collections.sort(crossTotals);
        return crossTotals;
    }

    /**
     * Gets cross totals in descending order.
     *
     * @return a collection of cross totals in descending order
     */
    public Collection<Integer> getCrossTotalsDescending() {
        List<Integer> crossTotals = new ArrayList<>(crossTotalTimestamps.keySet());
        crossTotals.sort(Collections.reverseOrder());
        return crossTotals;
    }

    /**
     * Gets timestamps for a specific cross total.
     *
     * @param crossTotal the cross total
     * @return a collection of timestamps
     */
    public Collection<Long> getTimestampsForResult(int crossTotal) {
        return crossTotalTimestamps.getOrDefault(crossTotal, Collections.emptyList());
    }
}
