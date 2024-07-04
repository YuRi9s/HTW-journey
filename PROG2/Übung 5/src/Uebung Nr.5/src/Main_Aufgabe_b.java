public class Main_Aufgabe_b {
    public static void main(String[] args) {
        // Testfälle
        // i) f(x) = x
        System.out.println("--------------------");
        System.out.println("f(x) = x");
        apply.applyAndPrint(1, 5, x -> x); // Lambda-Ausdruck

        // ii) f(x) = x!
        System.out.println("--------------------");
        System.out.println("f(x) = x! as a Top-Level-Klasse");
        apply.applyAndPrint(1, 5, new FactorialFunction()); // Top-Level-Klasse
        // Anonyme Klasse
        System.out.println("f(x) = x! as an Anonyme Klasse");
        apply.applyAndPrint(1, 5, x -> {
            int result = 1;
            for (int k = 1; k <= x; k++) {
                result *= k;
            }
            return result;
        });

        // iii) f(x) = x^(x+1)
        System.out.println("--------------------");
        System.out.println("f(x) = x^(x+1)");
        apply.applyAndPrint(1, 5, x -> (int) Math.pow(x, x + 1)); // Lambda-Ausdruck

        // iv) f(x) = fib(x) (Fibonacci-Folge)
        System.out.println("--------------------");
        System.out.println("f(x) = fib(x) ");
        apply.applyAndPrint(1, 5, new FibonacciFunction()); // Top-Level-Klasse
    }

    // Top-Level-Klasse für die Faktorielle-Funktion
    public static class FactorialFunction implements MyFunction {
        @Override
        public int apply(int x) {
            int result = 1;
            for (int k = 1; k <= x; k++) {
                result *= k;
            }
            return result;
        }
    }

    // Top-Level-Klasse für die Fibonacci-Funktion
    public static class FibonacciFunction implements MyFunction {
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
}
