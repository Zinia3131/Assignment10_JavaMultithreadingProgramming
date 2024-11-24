import java.util.LinkedList;
import java.util.Queue;

class Buffer {
    private final int capacity;
    private final Queue<Integer> queue = new LinkedList<>();

    // Constructor
    public Buffer(int capacity) {
        this.capacity = capacity;
    }

    // Add an item to the buffer
    public synchronized void produce(int item) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println("Buffer is full. Producer is waiting...");
            wait(); // Wait if the buffer is full
        }
        queue.add(item);
        System.out.println("Produced: " + item);
        notifyAll(); // Notify consumer that an item is available
    }

    // Remove an item from the buffer
    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Buffer is empty. Consumer is waiting...");
            wait(); // Wait if the buffer is empty
        }
        int item = queue.poll();
        System.out.println("Consumed: " + item);
        notifyAll(); // Notify producer that there is space
        return item;
    }
}

