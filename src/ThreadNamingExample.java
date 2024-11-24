public class ThreadNamingExample {
    public static void main(String[] args) {
        // Create multiple threads with different names
        Thread thread1 = new Thread(new NamedRunnable(), "Thread-Alpha");
        Thread thread2 = new Thread(new NamedRunnable(), "Thread-Beta");
        Thread thread3 = new Thread(new NamedRunnable(), "Thread-Gamma");

        // Start the threads
        thread1.start();
        thread2.start();
        thread3.start();

        // Print a message from the main thread
        System.out.println("Main thread is named: " + Thread.currentThread().getName());
    }
}
