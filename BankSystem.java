class BankAccount {
    private int balance = 1000; // Initial balance

    // Synchronized deposit
    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " deposited ₹" + amount + ". New balance: ₹" + balance);
    }

    // Synchronized withdraw
    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out
                    .println(Thread.currentThread().getName() + " withdrew ₹" + amount + ". New balance: ₹" + balance);
        } else {
            System.out.println(
                    Thread.currentThread().getName() + " tried to withdraw ₹" + amount + " but insufficient balance!");
        }
    }

    public int getBalance() {
        return balance;
    }
}

public class BankSystem {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(); // Shared account

        // Creating multiple users accessing the same account
        Thread user1 = new Thread(() -> {
            account.deposit(500);
            account.withdraw(700);
        }, "User 1");

        Thread user2 = new Thread(() -> {
            account.withdraw(800);
            account.deposit(400);
        }, "User 2");

        user1.start();
        user2.start();
    }
}
