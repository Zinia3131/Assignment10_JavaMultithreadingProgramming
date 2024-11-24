import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DeadlockExample {

    // Two resources that can cause deadlock
    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();

    public static void main(String[] args) {
        // Create threads that can potentially cause a deadlock
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread().getName() + " locked resource1");

                try {
                    // Simulate some work
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " was interrupted.");
                }

                synchronized (resource2) {
                    System.out.println(Thread.currentThread().getName() + " locked resource2");
                }
            }
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println(Thread.currentThread().getName() + " locked resource2");

                try {
                    // Simulate some work
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " was interrupted.");
                }

                synchronized (resource1) {
                    System.out.println(Thread.currentThread().getName() + " locked resource1");
                }
            }
        }, "Thread-2");

        // Start both threads
        thread1.start();
        thread2.start();

        // Start a thread to detect deadlocks
        Thread deadlockDetector = new Thread(() -> {
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
            while (true) {
                long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
                if (deadlockedThreads != null) {
                    System.out.println("Deadlock detected!");

                    ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(deadlockedThreads);
                    for (ThreadInfo threadInfo : threadInfos) {
                        System.out.println("Thread involved in deadlock: " + threadInfo.getThreadName());
                    }

                    // Exit after detecting deadlock (or handle it appropriately)
                    System.out.println("Exiting after deadlock detection.");
                    System.exit(1);
                }

                try {
                    Thread.sleep(500); // Check for deadlock every 500ms
                } catch (InterruptedException e) {
                    System.out.println("Deadlock detector was interrupted.");
                }
            }
        });

        deadlockDetector.setDaemon(true); // Ensure this thread does not prevent the application from exiting
        deadlockDetector.start();
    }
}

