import java.util.Scanner;

public class ATMInterface {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount(1000.0); 
        ATM atm = new ATM(myAccount);
        atm.start();
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double checkBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;

        System.out.println("Welcome to the ATM!");

        do {
            showMenu();
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next(); 
            }

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);
    }

    private void showMenu() {
        System.out.println("\n--- ATM MENU ---");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = getValidAmount();

        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. Current balance: $" + account.checkBalance());
        } else {
            System.out.println("Withdrawal failed. Insufficient balance or invalid amount.");
        }
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = getValidAmount();

        if (account.deposit(amount)) {
            System.out.println("Deposit successful. Current balance: $" + account.checkBalance());
        } else {
            System.out.println("Deposit failed. Invalid amount.");
        }
    }

    private void checkBalance() {
        System.out.println("Current balance: $" + account.checkBalance());
    }

    private double getValidAmount() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Enter a valid number: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
