import java.util.function.Predicate;

public interface MyFunctionWithConditionals extends MyFunction {
    static MyFunctionWithConditionals conditionateInput(Predicate<Integer> predicate) {
        return x -> predicate.test(x) ? x : 0;
    }

    static MyFunctionWithConditionals conditionateOutput(Predicate<Integer> predicate) {
        return x -> predicate.test(x) ? x : 0;
    }
}