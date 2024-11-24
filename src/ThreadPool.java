import java.util.LinkedList;
import java.util.Queue;


public class ThreadPool {
    private final int poolSize;
    private final Queue<Runnable> taskQueue;
    private final WorkerThread[] workers;
    private int activeThreads = 0;  // Counter for active threads

    public ThreadPool(int poolSize) {
        this.poolSize = poolSize;
        this.taskQueue = new LinkedList<>();
        this.workers = new WorkerThread[poolSize];

        // Create and start WorkerThreads
        for (int i = 0; i < poolSize; i++) {
            workers[i] = new WorkerThread(taskQueue, this);
            new Thread(workers[i]).start();
        }
    }

    public synchronized void submitTask(Runnable task) {
        synchronized (taskQueue) {
            taskQueue.offer(task);
            taskQueue.notify();  // Notify waiting worker threads
        }
        incrementActiveThreads();
    }

    public synchronized void incrementActiveThreads() {
        activeThreads++;
    }

    public synchronized void decrementActiveThreads() {
        activeThreads--;
    }

    public int getActiveThreads() {
        return activeThreads;
    }
}
