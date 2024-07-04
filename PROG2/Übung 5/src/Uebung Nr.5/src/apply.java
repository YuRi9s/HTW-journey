public class apply {
    public static void applyAndPrint(int i, int j, MyFunction f) {
        for (int k = i; k <= j; k++) {
            int result = f.apply(k);
            System.out.println("f(" + k + ") = " + result);
        }
    }
}
