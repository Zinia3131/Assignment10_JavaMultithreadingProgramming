class NamedRunnable implements Runnable {
    @Override
    public void run() {
        // Print the name of the current thread
        System.out.println("Hello from " + Thread.currentThread().getName() + "!");
    }
}