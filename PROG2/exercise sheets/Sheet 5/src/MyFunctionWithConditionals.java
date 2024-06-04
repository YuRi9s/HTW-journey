import java.util.function.Predicate;

/**
 * Represents an extended functional interface with default methods for conditional operations.
 * This interface extends the {@link MyFunction} interface.
 */
public interface MyFunctionWithConditionals extends MyFunction {

    /**
     * Returns a new function that conditionally applies the given predicate to the input integer.
     * If the predicate test passes, the original function's result is returned, otherwise returns 0.
     *
     * @param predicate the predicate to be applied conditionally
     * @return a new function with input conditionally applied
     */
    static MyFunctionWithConditionals conditionateInput(Predicate<Integer> predicate) {
        return x -> predicate.test(x) ? x : 0;
    }

    /**
     * Returns a new function that conditionally applies the given predicate to the output integer.
     * If the predicate test passes, the original function's result is returned, otherwise returns 0.
     *
     * @param predicate the predicate to be applied conditionally
     * @return a new function with output conditionally applied
     */
    static MyFunctionWithConditionals conditionateOutput(Predicate<Integer> predicate) {
        return x -> predicate.test(x) ? x : 0;
    }
}
