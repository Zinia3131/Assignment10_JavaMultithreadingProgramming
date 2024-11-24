class CounterTask implements Runnable {
    private Counter counter;

    // Constructor
    public CounterTask(Counter counter) {
        this.counter = counter;
    }
    @Override
    public void run() {
        // Increment
        for (int i = 0; i < 5; i++) {
            counter.increment();
            try {
                // Pause briefly to simulate some work
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted.");
            }
        }
    }
}

