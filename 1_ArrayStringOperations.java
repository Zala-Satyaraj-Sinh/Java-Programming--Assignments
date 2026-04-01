import java.util.Arrays;
import java.util.Scanner;

class ArrayOperations {
    private int[] arr;

    public ArrayOperations(int[] arr) {
        this.arr = arr;
    }

    public void reverse() {
        System.out.println("Original Array: " + Arrays.toString(arr));
        int[] reversedArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            reversedArr[i] = arr[arr.length - 1 - i];
        }
        System.out.println("Reversed Array: " + Arrays.toString(reversedArr));
    }

    public void sort() {
        System.out.println("Original Array: " + Arrays.toString(arr));
        int[] sortedArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sortedArr);
        System.out.println("Sorted Array: " + Arrays.toString(sortedArr));
    }

    public void search(int key) {
        boolean found = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                System.out.println("Element " + key + " found at index " + i);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Element " + key + " not found in the array.");
        }
    }

    public void average() {
        double sum = 0;
        for (int num : arr) {
            sum += num;
        }
        double average = sum / arr.length;
        System.out.println("Average of array elements: " + average);
    }

    public void maximum() {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println("Maximum element in array: " + max);
    }
}

class StringOperations {
    private String str;

    public StringOperations(String str) {
        this.str = str;
    }

    public void reverse() {
        System.out.println("Original String: " + str);
        StringBuilder reversedStr = new StringBuilder(str);
        System.out.println("Reversed String: " + reversedStr.reverse().toString());
    }

    public void sort() {
        System.out.println("Original String: " + str);
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        System.out.println("Sorted String: " + new String(charArray));
    }

    public void search(char key) {
        boolean found = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == key) {
                System.out.println("Character '" + key + "' found at index " + i);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Character '" + key + "' not found in the string.");
        }
    }
}

public class ArrayStringOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Demonstrate Array Operations
        System.out.println("--- Array Operations ---");
        int[] numbers = { 5, 2, 8, 1, 9, 4 };
        ArrayOperations arrayOps = new ArrayOperations(numbers);
        arrayOps.reverse();
        arrayOps.sort();
        arrayOps.average();
        arrayOps.maximum();
        System.out.print("Enter an element to search in the array: ");
        int arrayKey = scanner.nextInt();
        arrayOps.search(arrayKey);
        scanner.nextLine(); // Consume newline

        System.out.println("\n--- String Operations ---");
        String text = "hellojava";
        StringOperations stringOps = new StringOperations(text);
        stringOps.reverse();
        stringOps.sort();
        System.out.print("Enter a character to search in the string: ");
        char charKey = scanner.nextLine().charAt(0);
        stringOps.search(charKey);

        scanner.close();
    }
}
