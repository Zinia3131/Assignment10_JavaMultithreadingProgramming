import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// Define a Task class that implements Runnable
class Task implements Runnable {
    private final int taskId;

    public Task(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Task " + taskId + " is starting. Executed by: " + Thread.currentThread().getName());
        try {
            // Simulate some work with sleep
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Task " + taskId + " was interrupted.");
        }
        System.out.println("Task " + taskId + " is completed. Executed by: " + Thread.currentThread().getName());
    }
}

