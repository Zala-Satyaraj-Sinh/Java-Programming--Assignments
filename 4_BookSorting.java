import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// --- Book class implementing Comparable ---
// This provides a "natural" sorting order for Book objects.
class Book implements Comparable<Book> {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return "Book [Title='" + title + "', Author='" + author + "', Year=" + year + "]";
    }

    // Implement the compareTo method for natural sorting (by title)
    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title);
    }
}

// --- Comparator for sorting by author ---
// This provides an alternative sorting strategy.
class AuthorComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b1.getAuthor().compareTo(b2.getAuthor());
    }
}

// --- Comparator for sorting by year ---
class YearComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return Integer.compare(b1.getYear(), b2.getYear());
    }
}

public class BookSorting {
    public static void main(String[] args) {
        List<Book> library = new ArrayList<>();
        library.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", 1954));
        library.add(new Book("Pride and Prejudice", "Jane Austen", 1813));
        library.add(new Book("To Kill a Mockingbird", "Harper Lee", 1960));
        library.add(new Book("1984", "George Orwell", 1949));
        library.add(new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979));

        System.out.println("--- Original List of Books ---");
        printLibrary(library);

        // --- 1. Sorting using Comparable (natural order: by title) ---
        Collections.sort(library);
        System.out.println("\n--- Sorted by Title (using Comparable) ---");
        printLibrary(library);

        // --- 2. Sorting using Comparator (by author) ---
        Collections.sort(library, new AuthorComparator());
        System.out.println("\n--- Sorted by Author (using Comparator) ---");
        printLibrary(library);

        // --- 3. Sorting using another Comparator (by year) ---
        Collections.sort(library, new YearComparator());
        System.out.println("\n--- Sorted by Year (using Comparator) ---");
        printLibrary(library);
    }

    public static void printLibrary(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
