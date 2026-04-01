import java.util.Scanner;

// Abstract class for a Cipher
abstract class Cipher {
    // Abstract methods to be implemented by subclasses
    public abstract String encrypt(String text);
    public abstract String decrypt(String text);
}

// Concrete subclass for a Caesar Cipher
class CaesarCipher extends Cipher {
    private int shift;

    public CaesarCipher(int shift) {
        this.shift = shift;
    }

    @Override
    public String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                result.append((char) (((character - base + shift) % 26) + base));
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    @Override
    public String decrypt(String text) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                // To decrypt, we shift in the opposite direction
                int shiftedChar = (character - base - shift);
                if (shiftedChar < 0) {
                    shiftedChar += 26;
                }
                result.append((char) (shiftedChar + base));
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
}

// Concrete subclass for a Substitution Cipher
class SubstitutionCipher extends Cipher {
    private String key;
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public SubstitutionCipher(String key) {
        if (key.length() != 26) {
            throw new IllegalArgumentException("Key must be 26 characters long.");
        }
        this.key = key.toLowerCase();
    }

    @Override
    public String encrypt(String text) {
        StringBuilder result = new StringBuilder();
        text = text.toLowerCase();
        for (char character : text.toCharArray()) {
            int index = ALPHABET.indexOf(character);
            if (index != -1) {
                result.append(key.charAt(index));
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    @Override
    public String decrypt(String text) {
        StringBuilder result = new StringBuilder();
        text = text.toLowerCase();
        for (char character : text.toCharArray()) {
            int index = key.indexOf(character);
            if (index != -1) {
                result.append(ALPHABET.charAt(index));
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
}


public class CipherSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- Caesar Cipher Demonstration ---
        System.out.println("--- Caesar Cipher ---");
        System.out.print("Enter text to encrypt: ");
        String plainTextCaesar = scanner.nextLine();
        System.out.print("Enter shift value (e.g., 3): ");
        int shift = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Cipher caesarCipher = new CaesarCipher(shift);
        String encryptedCaesar = caesarCipher.encrypt(plainTextCaesar);
        System.out.println("Encrypted Text: " + encryptedCaesar);

        String decryptedCaesar = caesarCipher.decrypt(encryptedCaesar);
        System.out.println("Decrypted Text: " + decryptedCaesar);


        // --- Substitution Cipher Demonstration ---
        System.out.println("\n--- Substitution Cipher ---");
        System.out.print("Enter text to encrypt: ");
        String plainTextSub = scanner.nextLine();
        // Example key: qwertzuiopasdfghjklyxcvbnm
        String substitutionKey = "qwertzuiopasdfghjklyxcvbnm";
        System.out.println("Using substitution key: " + substitutionKey);

        try {
            Cipher substitutionCipher = new SubstitutionCipher(substitutionKey);
            String encryptedSub = substitutionCipher.encrypt(plainTextSub);
            System.out.println("Encrypted Text: " + encryptedSub);

            String decryptedSub = substitutionCipher.decrypt(encryptedSub);
            System.out.println("Decrypted Text: " + decryptedSub);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
