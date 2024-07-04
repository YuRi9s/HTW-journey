import java.util.*;

/**
 * Simulates the producer-consumer problem using a queue and random operations.
 */
public class Main {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>(); // For FIFO
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            if (random.nextInt(2) > 0) {
                queue.offer(producer.produce());
            } else if (!queue.isEmpty()) {
                consumer.consume(queue.poll());
            }
        }

        // Display some results
        System.out.println("Number of different cross totals: " + consumer.numberOfDifferentResults());
        System.out.println("Cross totals in ascending order: " + consumer.getCrossTotalsAscending());
        System.out.println("Cross totals in descending order: " + consumer.getCrossTotalsDescending());
    }
}
