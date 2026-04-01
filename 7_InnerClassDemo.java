// Outer class
class OuterClass {
    private String outerField = "This is the outer field.";

    // 1. Member Inner Class
    // It is a class created within another class but outside a method.
    // It can access all members (data and methods) of the outer class, including private ones.
    class MemberInnerClass {
        public void display() {
            System.out.println("--- Member Inner Class ---");
            System.out.println("Accessing outer field from member inner class: " + outerField);
        }
    }

    // Method that uses a local inner class
    public void useLocalInnerClass() {
        // 2. Local Inner Class
        // It is a class created inside a method.
        // Its scope is limited to the method where it is defined.
        class LocalInnerClass {
            public void display() {
                System.out.println("\n--- Local Inner Class ---");
                // Can still access the outer class's fields
                System.out.println("Accessing outer field from local inner class: " + outerField);
            }
        }

        // Create an instance of the local inner class and use it
        LocalInnerClass local = new LocalInnerClass();
        local.display();
    }

    // Method that uses an anonymous inner class
    public void useAnonymousInnerClass() {
        // 3. Anonymous Inner Class
        // It is a class without a name, used to override a method of a class or an interface.
        // Here, we create an anonymous inner class that extends the `Runnable` interface.
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("\n--- Anonymous Inner Class ---");
                System.out.println("This is running inside an anonymous inner class.");
                System.out.println("Accessing outer field from anonymous inner class: " + outerField);
            }
        }; // Semicolon is required here for the variable declaration

        // Run the code in the anonymous class
        new Thread(myRunnable).start();
    }
}

public class InnerClassDemo {
    public static void main(String[] args) {
        // Create an instance of the outer class
        OuterClass outer = new OuterClass();

        // --- Demonstrating Member Inner Class ---
        // To instantiate a member inner class, you need an instance of the outer class.
        OuterClass.MemberInnerClass memberInner = outer.new MemberInnerClass();
        memberInner.display();

        // --- Demonstrating Local Inner Class ---
        // The local inner class is instantiated and used within the `useLocalInnerClass` method.
        outer.useLocalInnerClass();

        // --- Demonstrating Anonymous Inner Class ---
        // The anonymous inner class is instantiated and used within the `useAnonymousInnerClass` method.
        outer.useAnonymousInnerClass();

        // A short delay to ensure the thread from the anonymous class completes
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
