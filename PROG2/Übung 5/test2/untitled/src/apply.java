import java.util.function.Predicate;

/**
 * Represents a utility class for applying and printing mathematical functions.
 */
public class apply {

    /**
     * Applies the given function to a range of integers and prints the results.
     *
     * @param i the start of the range (inclusive)
     * @param j the end of the range (inclusive)
     * @param f the function to apply
     */
    public static void applyAndPrint(int i, int j, MyFunction f) {
        for (int k = i; k <= j; k++) {
            int result = f.apply(k);
            System.out.println("f(" + k + ") = " + result);
        }
    }

    /**
     * Applies the given function to a range of integers and prints the results
     * if the predicate test passes for each integer.
     *
     * @param i         the start of the range (inclusive)
     * @param j         the end of the range (inclusive)
     * @param f         the function to apply
     * @param predicate the predicate to test each integer
     */
    public static void applyAndPrint_case2(int i, int j, MyFunction f, Predicate<Integer> predicate) {
        for (int k = i; k <= j; k++) {
            if (predicate.test(k)) {
                int result = f.apply(k);
                System.out.println("f(" + k + ") = " + result);
            }
        }
    }

    /**
     * Prints a line of dashes as a separator.
     */
    public static void printline() {
        System.out.println("--------------------");
    }
}
