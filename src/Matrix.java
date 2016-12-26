import java.util.ArrayList;

/**
 * Created by marek on 2016-12-23.
 */
public class Matrix {
    private int rowsNumber;
    private int columnsNumber;
    private int size;
    private int[] array;

    public Matrix(int r, int c) {
        rowsNumber = r;
        columnsNumber = c;
        size = rowsNumber * columnsNumber;
        array = new int[size];
    }

    public int getNumberOfRows() {
        return rowsNumber;
    }

    public int getNumberOfColumns() {
        return columnsNumber;
    }

    public void add(int r, int c, int value) {
        array[r*columnsNumber+c] = value;
    }

    public int get(int r, int c) {
        return array[r*columnsNumber+c];
    }

    public Matrix transpose() {
        Matrix result = new Matrix(columnsNumber, rowsNumber);
        for (int i = 0; i< rowsNumber; i++) {
            for (int j = 0; j< columnsNumber; j++) {
                result.add(j,i,this.get(i,j));
            }
        }
        return result;
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        if (a.getNumberOfColumns() != b.getNumberOfRows()) {
            return null;
        }
        Matrix result = new Matrix(a.getNumberOfRows(),b.getNumberOfColumns());
        for (int i=0; i<result.getNumberOfRows(); i++) {
            for (int j=0; j<result.getNumberOfColumns(); j++) {
                int newValue = 0;
                for (int k=0; k<a.getNumberOfColumns(); k++) {
                    newValue += a.get(i,k) * b.get(k,j);
                }
                result.add(i,j,newValue);
            }
        }
        return result;
    }
}
