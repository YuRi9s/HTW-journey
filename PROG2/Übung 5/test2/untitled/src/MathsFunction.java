import java.util.function.Predicate;

/**
 * Represents a utility class for mathematical functions and predicates.
 */
public class MathsFunction {

    /**
     * Represents a top-level class for the factorial function.
     * Implements the {@link MyFunction} interface.
     */
    static class FactorialFunction implements MyFunction {
        /**
         * Calculates the factorial of the given integer.
         *
         * @param x the integer input
         * @return the factorial of the input
         */
        @Override
        public int apply(int x) {
            int result = 1;
            for (int k = 1; k <= x; k++) {
                result *= k;
            }
            return result;
        }
    }

    /**
     * Represents a top-level class for the Fibonacci function.
     * Implements the {@link MyFunction} interface.
     */
    static class FibonacciFunction implements MyFunction {
        /**
         * Calculates the Fibonacci number for the given index.
         *
         * @param x the index of the Fibonacci number
         * @return the Fibonacci number at the given index
         */
        @Override
        public int apply(int x) {
            return fibonacci(x);
        }

        private int fibonacci(int n) {
            if (n <= 1)
                return n;
            else
                return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    /**
     * Represents a predicate for testing if a number is odd.
     * Implements the {@link Predicate} interface.
     */
    static class OddPredicate implements Predicate<Integer> {
        /**
         * Tests if the given integer is odd.
         *
         * @param x the integer to test
         * @return true if the integer is odd, false otherwise
         */
        @Override
        public boolean test(Integer x) {
            return x % 2 != 0;
        }
    }
}
