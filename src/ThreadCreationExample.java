public class ThreadCreationExample {
    public static void main(String[] args) {
        // Create an instance of the Runnable class
        MyRunnable myRunnable = new MyRunnable();

        // Create a Thread object and pass the Runnable instance
        Thread thread = new Thread(myRunnable);

        // Start the thread
        thread.start();

        // Print a message from the main thread
        System.out.println("This message is from the main thread.");
    }
}
