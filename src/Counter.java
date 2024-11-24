class Counter {
    private int count = 0;

    // Increment the counter in a synchronized method
    public synchronized void increment() {
        count++;
        System.out.println(Thread.currentThread().getName() + " incremented the counter to: " + count);
    }

    // Get the current value of the counter
    public int getValue() {
        return count;
    }
}
