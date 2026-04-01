import java.util.LinkedList;
import java.util.Queue;

// A shared buffer with a fixed size
class Buffer {
    private Queue<Integer> list;
    private int capacity;

    public Buffer(int capacity) {
        this.list = new LinkedList<>();
        this.capacity = capacity;
    }

    // Producer adds an item to the buffer
    public synchronized void produce(int item) throws InterruptedException {
        // Wait if the buffer is full
        while (list.size() == capacity) {
            System.out.println("Buffer is full. Producer is waiting...");
            wait();
        }

        list.add(item);
        System.out.println("Produced: " + item);

        // Notify the consumer that an item is available
        notify();
    }

    // Consumer removes an item from the buffer
    public synchronized int consume() throws InterruptedException {
        // Wait if the buffer is empty
        while (list.isEmpty()) {
            System.out.println("Buffer is empty. Consumer is waiting...");
            wait();
        }

        int item = list.poll();
        System.out.println("Consumed: " + item);

        // Notify the producer that space is available
        notify();
        return item;
    }
}

// Producer thread
class Producer implements Runnable {
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) { // Produce 10 items
            try {
                buffer.produce(i);
                Thread.sleep((long) (Math.random() * 1000)); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

// Consumer thread
class Consumer implements Runnable {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) { // Consume 10 items
            try {
                buffer.consume();
                Thread.sleep((long) (Math.random() * 1500)); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class ProducerConsumerProblem {
    public static void main(String[] args) {
        System.out.println("--- Producer-Consumer Problem using wait() and notify() ---");
        Buffer buffer = new Buffer(5); // Buffer with capacity 5

        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread = new Thread(new Consumer(buffer));

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\nProducer and Consumer have finished their work.");
    }
}
