import java.math.BigDecimal;

/**
 * A class for performing mathematical operations on matrices of arbitrary numeric types.
 */
public class Matrix<T extends Number> {
    private BigDecimal[][] data;
    int rows;
    int columns;


    /**
     * Constructs a new Matrix with the specified number of rows and columns.
     *
     * @param rows    The number of rows in the matrix.
     * @param columns The number of columns in the matrix.
     */
    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new BigDecimal[rows][columns];
    }

    /**
     * Sets the value of the specified cell in the matrix.
     *
     * @param i     The row index.
     * @param j     The column index.
     * @param value The value to set.
     */
    public void set(int i, int j, T value) {
        this.data[i][j] = new BigDecimal(value.toString());
    }

    /**
     * Gets the value of the specified cell in the matrix.
     *
     * @param i The row index.
     * @param j The column index.
     * @return The value of the specified cell.
     */
    public T get(int i, int j) {
        return (T) this.data[i][j];
    }

    /**
     * Adds another matrix to this matrix and returns the result as a new matrix.
     *
     * @param other The matrix to add.
     * @return A new matrix representing the sum of this matrix and the other matrix.
     * @throws IllegalArgumentException if the matrices have different dimensions.
     */
    public Matrix<T> add(Matrix<T> other) {
        if (this.rows != other.rows || this.columns != other.columns) {
            throw new IllegalArgumentException("Matrices must have the same dimensions");
        }
        if (this.rows <= 0 || this.columns <= 0) {
            throw new IllegalArgumentException("Matrices cant be zero or negative");
        }
        Matrix<T> result = new Matrix<>(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                BigDecimal sum = this.data[i][j].add(other.data[i][j]);
                result.set(i, j, (T) sum);
            }
        }
        return result;
    }

    /**
     * Multiplies each cell of the matrix by a scalar value and returns the result as a new matrix.
     *
     * @param scalar The scalar value to multiply by.
     * @return A new matrix representing the result of the scalar multiplication.
     */
    public Matrix<T> scalarMultiply(int scalar) {
        Matrix<T> result = new Matrix<>(rows, columns);
        BigDecimal scalarBigDecimal = BigDecimal.valueOf(scalar);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                BigDecimal product = this.data[i][j].multiply(scalarBigDecimal);
                result.set(i, j, (T) product);
            }
        }
        return result;
    }

    /**
     * Prints the matrix to the console in a readable format.
     */
    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(data[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
