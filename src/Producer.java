class Producer implements Runnable {
    private final Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                buffer.produce(i); // Produce an item
                Thread.sleep(200); // Simulate time to produce
            }
        } catch (InterruptedException e) {
            System.out.println("Producer interrupted.");
        }
    }
}

