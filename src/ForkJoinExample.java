import java.util.concurrent.ForkJoinPool;
import java.util.Random;

public class ForkJoinExample {
    public static void main(String[] args) {
        // Generate a large array of random numbers
        int size = 10_000_000;
        int[] array = new Random().ints(size, 1, 100).toArray();

        // Single-threaded computation
        long startTime = System.currentTimeMillis();
        long singleThreadSum = computeSequentially(array);
        long singleThreadTime = System.currentTimeMillis() - startTime;
        System.out.println("Single-threaded sum: " + singleThreadSum);
        System.out.println("Single-threaded time: " + singleThreadTime + " ms");

        // Parallel computation using Fork-Join
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ParallelSum task = new ParallelSum(array, 0, array.length);

        startTime = System.currentTimeMillis();
        long parallelSum = forkJoinPool.invoke(task);
        long parallelTime = System.currentTimeMillis() - startTime;
        System.out.println("Parallel sum: " + parallelSum);
        System.out.println("Parallel time: " + parallelTime + " ms");

        // Compare performance
        System.out.println("Performance improvement: " +
                ((double) singleThreadTime / parallelTime) + "x");
    }

    private static long computeSequentially(int[] array) {
        long sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }
}