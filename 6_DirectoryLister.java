import java.io.File;
import java.util.Scanner;

public class DirectoryLister {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the path of the directory to list: ");
        String path = scanner.nextLine();

        File directory = new File(path);

        // Check if the provided path exists and is a directory
        if (!directory.exists()) {
            System.out.println("Error: The specified path does not exist.");
            scanner.close();
            return;
        }

        if (!directory.isDirectory()) {
            System.out.println("Error: The specified path is not a directory.");
            scanner.close();
            return;
        }

        System.out.println("\n--- Listing contents of: " + directory.getAbsolutePath() + " ---");

        // listFiles() returns an array of File objects
        File[] filesAndDirs = directory.listFiles();

        if (filesAndDirs == null || filesAndDirs.length == 0) {
            System.out.println("The directory is empty.");
        } else {
            // Sort the array to display directories first, then files
            java.util.Arrays.sort(filesAndDirs, (f1, f2) -> {
                if (f1.isDirectory() && !f2.isDirectory()) {
                    return -1;
                }
                if (!f1.isDirectory() && f2.isDirectory()) {
                    return 1;
                }
                return f1.getName().compareToIgnoreCase(f2.getName());
            });

            for (File item : filesAndDirs) {
                if (item.isDirectory()) {
                    System.out.println("[D] " + item.getName());
                } else {
                    System.out.println("[F] " + item.getName());
                }
            }
        }

        scanner.close();
    }
}
