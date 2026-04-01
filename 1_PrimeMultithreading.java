import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PrimeMultithreading {

    // Method to check if a number is prime
    private static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // --- 1. Using the Thread class ---
    static class PrimeThread extends Thread {
        private int start;
        private int end;
        private List<Integer> primes = new ArrayList<>();

        public PrimeThread(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i <= end; i++) {
                if (isPrime(i)) {
                    primes.add(i);
                }
            }
        }

        public List<Integer> getPrimes() {
            return primes;
        }
    }

    // --- 2. Using the Runnable interface ---
    static class PrimeRunnable implements Runnable {
        private int start;
        private int end;
        private List<Integer> primes = new ArrayList<>();

        public PrimeRunnable(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i <= end; i++) {
                if (isPrime(i)) {
                    primes.add(i);
                }
            }
        }

        public List<Integer> getPrimes() {
            return primes;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int range = 100;
        System.out.println("Finding prime numbers up to " + range + " using different multithreading approaches.\n");

        // --- Method 1: Extending Thread ---
        System.out.println("--- 1. Using Thread Class ---");
        PrimeThread t1 = new PrimeThread(1, range / 2);
        PrimeThread t2 = new PrimeThread(range / 2 + 1, range);
        t1.start();
        t2.start();
        t1.join(); // Wait for t1 to finish
        t2.join(); // Wait for t2 to finish
        List<Integer> threadPrimes = new ArrayList<>(t1.getPrimes());
        threadPrimes.addAll(t2.getPrimes());
        System.out.println("Primes found: " + threadPrimes);

        // --- Method 2: Implementing Runnable ---
        System.out.println("\n--- 2. Using Runnable Interface ---");
        PrimeRunnable r1 = new PrimeRunnable(1, range / 2);
        PrimeRunnable r2 = new PrimeRunnable(range / 2 + 1, range);
        Thread rt1 = new Thread(r1);
        Thread rt2 = new Thread(r2);
        rt1.start();
        rt2.start();
        rt1.join();
        rt2.join();
        List<Integer> runnablePrimes = new ArrayList<>(r1.getPrimes());
        runnablePrimes.addAll(r2.getPrimes());
        System.out.println("Primes found: " + runnablePrimes);

        // --- Method 3: Using Executor Framework ---
        System.out.println("\n--- 3. Using Executor Framework ---");
        ExecutorService executor = Executors.newFixedThreadPool(2); // Pool of 2 threads
        List<Integer> executorPrimes = new ArrayList<>();

        // Create a task to be executed
        Runnable task = () -> {
            // This is a simple example; a real implementation would divide the range
            // more intelligently among tasks.
            for (int i = 1; i <= range; i++) {
                if (isPrime(i)) {
                    // Synchronize access to the shared list
                    synchronized (executorPrimes) {
                        if (!executorPrimes.contains(i)) {
                             executorPrimes.add(i);
                        }
                    }
                }
            }
        };

        executor.submit(task);
        executor.submit(task); // Submit tasks to the pool

        executor.shutdown(); // Initiates an orderly shutdown
        executor.awaitTermination(1, TimeUnit.MINUTES); // Wait for tasks to complete
        executorPrimes.sort(Integer::compareTo);
        System.out.println("Primes found: " + executorPrimes);
    }
}
