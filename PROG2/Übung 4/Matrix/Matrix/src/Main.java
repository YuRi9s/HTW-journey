import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();

        System.out.print("Enter the number of columns: ");
        int columns = scanner.nextInt();

        Matrix<Integer> matrix1 = new Matrix<>(rows, columns);
        Matrix<Integer> matrix2 = new Matrix<>(rows, columns);

        System.out.println("Enter elements of first matrix:");
        enterMatrixElements(matrix1, scanner);

        System.out.println("Enter elements of second matrix:");
        enterMatrixElements(matrix2, scanner);

        Matrix<Integer> resultAddition = matrix1.add(matrix2);
        System.out.println("Addition:");
        resultAddition.print();

        System.out.println("\nScalar Multiplication (Enter scalar value):");
        int scalar = scanner.nextInt();
        Matrix<Integer> resultScalarMultiply = matrix1.scalarMultiply(scalar);
        resultScalarMultiply.print();
    }

    private static <T extends Number> void enterMatrixElements(Matrix<T> matrix, Scanner scanner) {
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.columns; j++) {
                System.out.print("Enter element [" + i + "][" + j + "]: ");
                T value = (T) Integer.valueOf(scanner.nextInt());
                matrix.set(i, j, value);
            }
        }
    }
}
