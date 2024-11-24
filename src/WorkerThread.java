import java.util.LinkedList;
import java.util.Queue;

// WorkerThread class implementing Runnable
class WorkerThread implements Runnable {
    private final Queue<Runnable> taskQueue;  // Queue to store tasks
    private final ThreadPool threadPool;      // Reference to the ThreadPool to track active threads

    public WorkerThread(Queue<Runnable> taskQueue, ThreadPool threadPool) {
        this.taskQueue = taskQueue;
        this.threadPool = threadPool;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (taskQueue) {
                // Wait for task to be available in the queue
                while (taskQueue.isEmpty()) {
                    try {
                        taskQueue.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return; // Exit if interrupted
                    }
                }

                // Take the next task from the queue
                Runnable task = taskQueue.poll();
                if (task != null) {
                    task.run(); // Execute the task
                }
            }

            // Decrement the active thread count in the thread pool
            threadPool.decrementActiveThreads();
        }
    }
}
