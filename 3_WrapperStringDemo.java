public class WrapperStringDemo {
    public static void main(String[] args) {
        // --- Wrapper Classes ---
        System.out.println("--- Wrapper Classes Demonstration ---");

        // Autoboxing: converting primitive to wrapper object
        int primitiveInt = 100;
        Integer wrapperInt = primitiveInt; // Autoboxing
        System.out.println("Primitive int: " + primitiveInt);
        System.out.println("Wrapper Integer: " + wrapperInt);

        // Unboxing: converting wrapper object to primitive
        Double wrapperDouble = 25.5;
        double primitiveDouble = wrapperDouble; // Unboxing
        System.out.println("Wrapper Double: " + wrapperDouble);
        System.out.println("Primitive double: " + primitiveDouble);

        // Using wrapper class methods
        String numStr = "123";
        int parsedInt = Integer.parseInt(numStr);
        System.out.println("Parsed int from string '" + numStr + "': " + parsedInt);
        System.out.println("Binary representation of 10: " + Integer.toBinaryString(10));
        System.out.println("Max value of an Integer: " + Integer.MAX_VALUE);

        // --- String vs StringBuffer ---
        System.out.println("\n--- String vs. StringBuffer Demonstration ---");

        // String is immutable
        System.out.println("\n--- String (Immutable) ---");
        String str = "Hello";
        System.out.println("Original String: " + str + " (hashCode: " + str.hashCode() + ")");
        str = str + " World"; // A new string object is created
        System.out.println("Modified String: " + str + " (hashCode: " + str.hashCode() + ")");
        // Notice the hash code is different, indicating a new object was created.

        // StringBuffer is mutable
        System.out.println("\n--- StringBuffer (Mutable) ---");
        StringBuffer sb = new StringBuffer("Hello");
        System.out.println("Original StringBuffer: " + sb + " (hashCode: " + sb.hashCode() + ")");
        sb.append(" World"); // The same object is modified
        System.out.println("Modified StringBuffer: " + sb + " (hashCode: " + sb.hashCode() + ")");
        // Notice the hash code is the same, indicating the object itself was changed.

        // Performance difference (conceptual example)
        // Concatenating strings in a loop is inefficient with String
        long startTime = System.nanoTime();
        String tempStr = "";
        for (int i = 0; i < 10000; i++) {
            tempStr += "a";
        }
        long endTime = System.nanoTime();
        System.out.println("\nTime taken for String concatenation: " + (endTime - startTime) + " ns");

        // StringBuffer is much more efficient for repeated modifications
        startTime = System.nanoTime();
        StringBuffer tempSb = new StringBuffer();
        for (int i = 0; i < 10000; i++) {
            tempSb.append("a");
        }
        endTime = System.nanoTime();
        System.out.println("Time taken for StringBuffer append: " + (endTime - startTime) + " ns");
    }
}
