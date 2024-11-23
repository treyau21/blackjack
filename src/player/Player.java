package player;

public class Player {

    private String name;
    private Account account;

    /*
     * Constructs a new player with a specified name and balance.
     */
    public Player(String name, int balance) {
        this.name = name;
        this.account =  new Account("MAIN", balance);
    }

    /*
     * Returns the name of the player.
     */
    public String getName() {
        return name;
    }

    /*
     * Returns the balance of the player.
     */
    public Account getAccount() {
        return this.account;
    }

    /*
     * Returns a string representation of the player.
     */
    public String toString() {
        return name + "\n" + account.toString();
    }
}
