import java.util.function.*;

public class Main_Aufgabe_d {

    public static void main(String[] args) {
        // Testfälle
        Predicate<Integer> odd = new OddPredicate(); // Anonyme Klasse
        Predicate<Integer> even = x -> x % 2 == 0; // Lambda-Ausdruck
    }

    // Prädikat für ungerade Zahlen
    public static class OddPredicate implements Predicate<Integer> {
        @Override
        public boolean test(Integer x) {
            return x % 2 != 0;
        }
    }
}
