import java.util.Scanner;

// Custom exception for insufficient funds
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// BankAccount class that can throw the custom exception
class BankAccountWithException {
    private double balance;

    public BankAccountWithException(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // This method throws our custom exception
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Successfully withdrew $" + amount);
        } else {
            double shortfall = amount - balance;
            // Throw the custom exception
            throw new InsufficientFundsException("Withdrawal failed. Insufficient funds. You are short by $" + shortfall);
        }
    }

    public void checkBalance() {
        System.out.println("Current Balance: $" + balance);
    }
}

public class CustomExceptionDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccountWithException account = new BankAccountWithException(1000.00);

        System.out.println("Bank account created with an initial balance of $1000.00");

        int choice;
        do {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    account.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    try {
                        // This is where the exception might be thrown
                        account.withdraw(withdrawAmount);
                    } catch (InsufficientFundsException e) {
                        // This block catches our custom exception
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using our bank services!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
             account.checkBalance();
        } while (choice != 4);

        scanner.close();
    }
}
