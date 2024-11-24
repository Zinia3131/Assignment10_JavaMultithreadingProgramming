public class ThreadSleepExample {
    public static void main(String[] args) {
        // Create multiple threads
        Thread thread1 = new Thread(new MyRunnableWithSleep("Thread-1"));
        Thread thread2 = new Thread(new MyRunnableWithSleep("Thread-2"));
        Thread thread3 = new Thread(new MyRunnableWithSleep("Thread-3"));

        // Start the threads
        thread1.start();
        thread2.start();
        thread3.start();

        // Print a message from the main thread
        System.out.println("All threads have been started.");
    }
}
