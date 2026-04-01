import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "sample.txt";

        // Step 1: Create a sample file to read from
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println("Hello world, this is a test file.");
            writer.println("This file is used to test the word count program.");
            writer.println("Hello again, world!");
            System.out.println("Created a sample file named '" + fileName + "'.");
        } catch (IOException e) {
            System.err.println("Error creating sample file: " + e.getMessage());
            return;
        }

        // Map to store word counts
        Map<String, Integer> wordCounts = new HashMap<>();

        // Step 2: Read the file and count word occurrences
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into words. Regex removes punctuation and splits by whitespace.
                String[] words = line.toLowerCase().replaceAll("[^a-zA-Z ]", "").split("\\s+");

                for (String word : words) {
                    if (!word.isEmpty()) {
                        // Get the current count of the word, or 0 if it's not in the map
                        int count = wordCounts.getOrDefault(word, 0);
                        // Increment the count and put it back in the map
                        wordCounts.put(word, count + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        // Step 3: Display the word counts
        System.out.println("\n--- Word Occurrences ---");
        if (wordCounts.isEmpty()) {
            System.out.println("No words found in the file.");
        } else {
            // Using entrySet for efficient iteration
            for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
                System.out.println("'" + entry.getKey() + "': " + entry.getValue() + " times");
            }
        }
        
        scanner.close();
    }
}
