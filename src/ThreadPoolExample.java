import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // Create a fixed thread pool with 3 threads
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        // Submit multiple tasks to the thread pool
        for (int i = 1; i <= 10; i++) {
            threadPool.submit(new Task(i));
        }

        // Properly shut down the thread pool
        threadPool.shutdown(); // Initiates an orderly shutdown
        System.out.println("Thread pool shutdown initiated.");

        try {
            // Wait for the termination of all tasks
            if (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("Not all tasks completed within the timeout.");
                threadPool.shutdownNow(); // Force shutdown if tasks are still running
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted during awaitTermination.");
            threadPool.shutdownNow(); // Force shutdown
        }

        System.out.println("Thread pool shutdown complete.");
    }
}
