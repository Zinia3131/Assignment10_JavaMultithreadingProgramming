import java.util.concurrent.RecursiveTask;


class ParallelSum extends RecursiveTask<Long> {
    private static final int THRESHOLD = 1_000; // Threshold for splitting tasks
    private final int[] array;
    private final int start;
    private final int end;

    public ParallelSum(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            // Compute directly if below threshold
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            // Split task into smaller subtasks
            int mid = (start + end) / 2;
            ParallelSum leftTask = new ParallelSum(array, start, mid);
            ParallelSum rightTask = new ParallelSum(array, mid, end);

            // Fork left task, compute right task
            leftTask.fork();
            long rightResult = rightTask.compute();
            long leftResult = leftTask.join(); // Wait for left task to finish

            // Combine results
            return leftResult + rightResult;
        }
    }
}

