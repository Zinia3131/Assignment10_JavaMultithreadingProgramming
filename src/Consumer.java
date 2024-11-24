class Consumer implements Runnable {
    private final Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                buffer.consume(); // Consume an item
                Thread.sleep(300); // Simulate time to consume
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer interrupted.");
        }
    }
}
