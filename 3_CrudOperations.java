import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

// A simple Item class for our inventory
class Item {
    private int id;
    private String name;
    private double price;

    public Item(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Item [ID=" + id + ", Name=" + name + ", Price=$" + price + "]";
    }
}

public class CrudOperations {
    // Using ArrayList for a simple list of items
    private static ArrayList<Item> arrayListItems = new ArrayList<>();
    // Using HashMap for fast lookup by item ID
    private static HashMap<Integer, Item> hashMapItems = new HashMap<>();
    // Using TreeMap to keep items sorted by ID
    private static TreeMap<Integer, Item> treeMapItems = new TreeMap<>();
    private static int nextId = 1;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- CRUD Operations Menu ---");
            System.out.println("1. Create (Add) a new Item");
            System.out.println("2. Read (View) all Items");
            System.out.println("3. Update an Item");
            System.out.println("4. Delete an Item");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: createItem(); break;
                case 2: readItems(); break;
                case 3: updateItem(); break;
                case 4: deleteItem(); break;
                case 5: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
        scanner.close();
    }

    // CREATE operation
    private static void createItem() {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter item price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Item newItem = new Item(nextId, name, price);
        arrayListItems.add(newItem);
        hashMapItems.put(nextId, newItem);
        treeMapItems.put(nextId, newItem);

        System.out.println("Item created successfully with ID: " + nextId);
        nextId++;
    }

    // READ operation
    private static void readItems() {
        if (arrayListItems.isEmpty()) {
            System.out.println("No items to display.");
            return;
        }
        System.out.println("\n--- Items in ArrayList ---");
        for (Item item : arrayListItems) {
            System.out.println(item);
        }

        System.out.println("\n--- Items in HashMap (order not guaranteed) ---");
        for (Item item : hashMapItems.values()) {
            System.out.println(item);
        }

        System.out.println("\n--- Items in TreeMap (sorted by ID) ---");
        for (Item item : treeMapItems.values()) {
            System.out.println(item);
        }
    }

    // UPDATE operation
    private static void updateItem() {
        System.out.print("Enter the ID of the item to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (hashMapItems.containsKey(id)) {
            Item itemToUpdate = hashMapItems.get(id);
            System.out.print("Enter new name (current: " + itemToUpdate.getName() + "): ");
            String newName = scanner.nextLine();
            System.out.print("Enter new price (current: " + itemToUpdate.getPrice() + "): ");
            double newPrice = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            // Update the item in all collections
            itemToUpdate.setName(newName);
            itemToUpdate.setPrice(newPrice);
            
            // For ArrayList, we need to find and replace
            for(int i=0; i<arrayListItems.size(); i++){
                if(arrayListItems.get(i).getId() == id){
                    arrayListItems.set(i, itemToUpdate);
                    break;
                }
            }

            System.out.println("Item updated successfully.");
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
    }

    // DELETE operation
    private static void deleteItem() {
        System.out.print("Enter the ID of the item to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (hashMapItems.containsKey(id)) {
            // Remove from HashMap and TreeMap
            hashMapItems.remove(id);
            treeMapItems.remove(id);

            // Remove from ArrayList
            Item toRemove = null;
            for (Item item : arrayListItems) {
                if (item.getId() == id) {
                    toRemove = item;
                    break;
                }
            }
            if (toRemove != null) {
                arrayListItems.remove(toRemove);
            }

            System.out.println("Item with ID " + id + " deleted successfully.");
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
    }
}
