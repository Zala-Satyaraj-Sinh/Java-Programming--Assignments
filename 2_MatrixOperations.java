import java.util.Scanner;

class Matrix {
    private int[][] matrix;
    private int rows;
    private int cols;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new int[rows][cols];
    }

    public Matrix(int[][] data) {
        this.rows = data.length;
        this.cols = data[0].length;
        this.matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.matrix[i][j] = data[i][j];
            }
        }
    }

    public void readMatrix(Scanner scanner) {
        System.out.println("Enter the elements of the matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
    }

    public void displayMatrix() {
        System.out.println("Matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public Matrix transpose() {
        Matrix result = new Matrix(cols, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.matrix[j][i] = this.matrix[i][j];
            }
        }
        return result;
    }

    public Matrix multiply(Matrix other) {
        if (this.cols != other.rows) {
            System.out.println("Matrix multiplication is not possible.");
            return null;
        }
        Matrix result = new Matrix(this.rows, other.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                for (int k = 0; k < this.cols; k++) {
                    result.matrix[i][j] += this.matrix[i][k] * other.matrix[k][j];
                }
            }
        }
        return result;
    }
}

public class MatrixOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter dimensions for the first matrix (rows cols):");
        int rows1 = scanner.nextInt();
        int cols1 = scanner.nextInt();
        Matrix m1 = new Matrix(rows1, cols1);
        m1.readMatrix(scanner);
        System.out.println("First Matrix:");
        m1.displayMatrix();

        System.out.println("\nTranspose of the first matrix:");
        Matrix transposeM1 = m1.transpose();
        transposeM1.displayMatrix();

        System.out.println("\nEnter dimensions for the second matrix (rows cols):");
        int rows2 = scanner.nextInt();
        int cols2 = scanner.nextInt();
        Matrix m2 = new Matrix(rows2, cols2);
        m2.readMatrix(scanner);
        System.out.println("Second Matrix:");
        m2.displayMatrix();

        System.out.println("\nMultiplication of the two matrices:");
        Matrix product = m1.multiply(m2);
        if (product != null) {
            product.displayMatrix();
        }

        scanner.close();
    }
}
