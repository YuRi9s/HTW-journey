import java.util.function.*;

public class Main_Aufgabe_c {
    public static void main(String[] args) {
        // Testfälle
        MyFunctionWithConditionals func1 = MyFunctionWithConditionals.conditionateInput(x -> x % 2 == 0);
        MyFunctionWithConditionals func2 = MyFunctionWithConditionals.conditionateOutput(x -> x % 2 != 0);

    }
}
