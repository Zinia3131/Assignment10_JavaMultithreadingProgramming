class MyRunnableWithSleep implements Runnable {
    private String threadName;

    // Constructor to set the thread name
    public MyRunnableWithSleep(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        try {
            // Print a message before sleep
            System.out.println(threadName + " is starting.");

            // Pause the thread for 3 seconds
            Thread.sleep(3000);

            // Print a message after sleep
            System.out.println(threadName + " has finished sleeping.");
        } catch (InterruptedException e) {
            System.out.println(threadName + " was interrupted.");
        }
    }
}

