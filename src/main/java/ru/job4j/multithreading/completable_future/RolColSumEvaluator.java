package ru.job4j.multithreading.completable_future;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RolColSumEvaluator {
    public Sums[] sum(int[][] matrix) {
        IntMatrix intMatrix = new IntMatrix(matrix);
        Sums[] result = new Sums[Math.max(intMatrix.getColumnCount(), intMatrix.getRowCount())];
        for (int i = 0; i < result.length; i++) {
            int rowSum = getArraySum(intMatrix.getRowOrNull(i));
            int columnSum = getArraySum(intMatrix.getColumnOrNull(i));
            result[i] = new Sums(rowSum, columnSum);
        }
        return result;
    }

    public Sums[] asyncSum(int[][] matrix) {
        IntMatrix intMatrix = new IntMatrix(matrix);
        List<CompletableFuture<Sums>> futureSums = new ArrayList<>();

        for (int i = 0; i < Math.max(intMatrix.getColumnCount(), intMatrix.getRowCount()); i++) {
            int counter = i;
            var rowSum = CompletableFuture
                    .supplyAsync(() -> getArraySum(intMatrix.getRowOrNull((counter))));
            var columnSum = CompletableFuture
                    .supplyAsync(() -> getArraySum(intMatrix.getColumnOrNull((counter))));
            futureSums.add(rowSum.thenCombine(columnSum, Sums::new));
        }
        return futureSums
                .stream()
                .map(CompletableFuture::join)
                .toArray(Sums[]::new);
    }

    private Integer getArraySum(int[] array) {
        if (array == null) return 0;
        return Arrays.stream(array).sum();
    }
}
