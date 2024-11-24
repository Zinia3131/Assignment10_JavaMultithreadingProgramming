class MyRunnable implements Runnable {
    @Override
    public void run() {
        // Code to execute in the thread
        System.out.println("Hello from the thread! This is a message from: " + Thread.currentThread().getName());
    }
}