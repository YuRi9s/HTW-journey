import java.util.function.Predicate;

/**
 * Represents the main class for testing the applyAndPrint method.
 */
public class Main {
    public static void main(String[] args) {
        // Test cases for the applyAndPrint method
        // i) f(x) = x
        apply.printline();
        apply.applyAndPrint(1, 5, (int x) -> x * x); // Lambda expression

        // ii) f(x) = x!
        apply.printline();
        apply.applyAndPrint(1, 5, new MathsFunction.FactorialFunction()); // Top-level class
        apply.printline();
        apply.applyAndPrint(1, 5, new MyFunction() { // Anonymous class
            @Override
            public int apply(int x) {
                int result = 1;
                for (int k = 1; k <= x; k++) {
                    result *= k;
                }
                return result;
            }
        });

        // iii) f(x) = x^(x+1)
        apply.printline();
        apply.applyAndPrint(1, 5,  x -> (int) Math.pow(x, x + 1)); // Lambda expression

        // iv) f(x) = fib(x) (Fibonacci sequence)
        apply.printline();
        apply.applyAndPrint(1, 5, new MathsFunction.FibonacciFunction()); // Top-level class

        // Test cases for the applyAndPrint method with conditions
        // e) Lambda expression for squares of even numbers
        apply.printline();
        Predicate<Integer> even = x -> x % 2 == 0;
        // test even_number = new even_number();
        apply.applyAndPrint(1, 10, (new MyFunctionWithConditionals() {

            @Override
            public int apply(int i) {
                return i * i;
            }

        }).conditionateInput(even));

        // f) Odd factorials
        apply.printline();
        Predicate<Integer> odd = new MathsFunction.OddPredicate(); // Anonymous class
        apply.applyAndPrint_case2(1, 5, new MathsFunction.FactorialFunction(), odd);
    }
}
