import java.util.Random;

/**
 * Generates random integers between 0 and 1000.
 */
public class Producer {
    private Random random = new Random();

    /**
     * Produces a random integer between 0 and 1000.
     *
     * @return a random integer
     */
    public int produce() {
        return random.nextInt(1001); // Generates a random integer between 0 and 1000
    }
}
