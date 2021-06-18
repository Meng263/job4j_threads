package ru.job4j.multithreading.completable_future;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class IntMatrixTest {
    private final int[][] initialMatrix = {
            {1, 2, 1,},
            {2, 2, 3,},
            {1, 4, 3,},
            {1, 5, 3,},
    };

    @Test
    public void columnTest() {
        IntMatrix matrix = new IntMatrix(initialMatrix);
        int[] thirdColumn = new int[]{1, 3, 3, 3,};

        assertArrayEquals(thirdColumn, matrix.getColumnOrNull(2));
    }

    @Test
    public void rowTest() {
        IntMatrix matrix = new IntMatrix(initialMatrix);
        int[] thirdRow = new int[]{1, 4, 3,};

        assertArrayEquals(thirdRow, matrix.getRowOrNull(2));
    }


    @Test
    public void rowOutOfBounceTest() {
        IntMatrix matrix = new IntMatrix(initialMatrix);
        assertNull(matrix.getRowOrNull(4));
    }

    @Test
    public void columnOutOfBounceTest() {
        IntMatrix matrix = new IntMatrix(initialMatrix);
        assertNull(matrix.getColumnOrNull(3));
    }
}