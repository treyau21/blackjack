package player;

public class Account {

    private String name;
    private double balance;

    /*
     * Constructs a new player with a specified name and balance.
     */
    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    /*
     * Returns the name of the account.
     */
    public String getName() {
        return this.name;
    }

    /*
     * Returns the balance of the player.
     */
    public double getBalance() {
        return this.balance;
    }

    /*
     * Adds the specified amount to the player's balance.
     */
    public void deposit(double amount) {
        this.balance += amount;
    }

    /*
     * Subtracts the specified amount from the player's balance.
     */
    public void withdraw(double amount) {
        this.balance -= amount;
    }

    /*
     * Returns a string representation of the player.
     */
    public String toString() {
        return "Account Balance [" + name + "]: $" + balance;
    }
}
