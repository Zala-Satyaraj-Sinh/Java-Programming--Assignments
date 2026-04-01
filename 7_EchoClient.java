import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) {
        String hostname = "localhost"; // Server is on the same machine
        int port = 12345;
        System.out.println("--- TCP Echo Client ---");

        try (
            // Create a socket to connect to the server
            Socket socket = new Socket(hostname, port);
            // Writer to send data to the server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Reader to get data from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Scanner to get user input from the console
            Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Connected to the echo server. Type 'exit' to quit.");
            String userInput;

            while (true) {
                System.out.print("Enter message to send: ");
                userInput = scanner.nextLine();

                // Send the user's message to the server
                out.println(userInput);

                // If the user types "exit", break the loop
                if ("exit".equalsIgnoreCase(userInput)) {
                    break;
                }

                // Read the server's response and print it
                String serverResponse = in.readLine();
                System.out.println("Server response: " + serverResponse);
            }

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostname);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostname + ". Is the server running?");
        }
    }
}
