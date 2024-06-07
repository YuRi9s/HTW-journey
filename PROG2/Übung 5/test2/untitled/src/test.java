public class test {
    public int even(int i) {
        int result = 0;
        for (int k = 0; k <= i; k++) {

            if (k % 2 == 0) {
                result = k;

            }
        }
        return result;
    }
}
