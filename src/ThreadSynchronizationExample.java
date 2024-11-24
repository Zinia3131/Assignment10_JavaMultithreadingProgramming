public class ThreadSynchronizationExample {
    public static void main(String[] args) {
        // Create a shared Counter instance
        Counter counter = new Counter();

        // Create multiple threads that share the Counter
        Thread thread1 = new Thread(new CounterTask(counter), "Thread-1");
        Thread thread2 = new Thread(new CounterTask(counter), "Thread-2");
        Thread thread3 = new Thread(new CounterTask(counter), "Thread-3");

        // Start the threads
        thread1.start();
        thread2.start();
        thread3.start();

        // Wait for threads to finish
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        // Print the final value of the counter
        System.out.println("Final counter value: " + counter.getValue());
    }
}
