public class CustomThreadPoolTest {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(3);  // Create a thread pool with 3 worker threads

        // Submit some tasks to the thread pool
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            threadPool.submitTask(() -> {
                System.out.println("Executing task " + taskId + " in thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate task execution
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Output active threads count
        System.out.println("Active threads: " + threadPool.getActiveThreads());
    }
}
