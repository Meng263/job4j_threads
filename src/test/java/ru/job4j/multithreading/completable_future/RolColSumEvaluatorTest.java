package ru.job4j.multithreading.completable_future;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RolColSumEvaluatorTest {
    private final int[][] initialMatrix = {
            {1, 2, 1,},
            {2, 2, 3,},
            {1, 4, 3,},
            {1, 5, 3,},
    };

    private final Sums[] expected = new Sums[]{
            new Sums(4, 5),
            new Sums(7, 13),
            new Sums(8, 10),
            new Sums(9, 0),
    };

    @Test
    public void evaluateSyncSum() {
        checkEvaluateSum(new RolColSumEvaluator()::sum);
    }

    @Test
    public void evaluateAsyncSum() {
        checkEvaluateSum(new RolColSumEvaluator()::asyncSum);
    }

    private void checkEvaluateSum(Function<int[][], Sums[]> sum) {
        Sums[] sums = sum.apply(initialMatrix);
        assertArrayEquals(expected, sums);
    }
}