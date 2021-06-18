package ru.job4j.multithreading.completable_future;

public class IntMatrix {
    private final int[][] matrix;
    private final int rowCount;
    private final int columnCount;

    public IntMatrix(int[][] matrix) {
        this.matrix = matrix;
        rowCount = matrix.length;
        columnCount = matrix[0].length;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int[] getColumnOrNull(int colNum) {
        if (colNum >= columnCount) return null;

        int[] result = new int[rowCount];

        for (int i = 0; i < result.length; i++) {
            result[i] = matrix[i][colNum];
        }
        return result;
    }

    public int[] getRowOrNull(int colNum) {
        if (colNum >= rowCount) return null;

        return matrix[colNum];
    }
}
