import java.util.concurrent.*;
import java.util.Map;

public class ConcurrentDataStructureExample {

    public static void main(String[] args) throws InterruptedException {
        // Create a ConcurrentHashMap
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();

        // Create a BlockingQueue
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        // Producer thread to add data to the queue
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    String value = "Value-" + i;
                    queue.put(value);
                    System.out.println(Thread.currentThread().getName() + " produced: " + value);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Consumer thread to take data from the queue and update the map
        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    String value = queue.take(); // Take from the queue
                    concurrentMap.put(value, value.length()); // Update the map
                    System.out.println(Thread.currentThread().getName() + " consumed: " + value);

                    // Break the loop when all items are processed
                    if (concurrentMap.size() >= 10) break;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Start both threads
        producer.start();
        consumer.start();

        // Wait for threads to complete
        producer.join();
        consumer.join();

        // Print the contents of the ConcurrentHashMap
        System.out.println("\nFinal contents of the ConcurrentHashMap:");
        for (Map.Entry<String, Integer> entry : concurrentMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

