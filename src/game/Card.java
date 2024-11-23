package game;

public class Card {

    private String suit;
    private String rank;

    /*
     * Constructs a new card with a specified suit and rank.
     */
    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /* 
     * Returns the suit of the card.
     */
    public String getSuit() {
        return suit;
    }

    /*
     * Returns the rank of the card.
     */
    public String getRank() {
        return rank;
    }

    /*
     * Returns the value of the card.
     */
    public int getValue() {
        if (rank.equals("A")) {
            return 1;
        } else if (rank.equals("T") || rank.equals("J") || rank.equals("Q") || rank.equals("K")) {
            return 10;
        } else {
            return Integer.parseInt(rank);
        }
    }   

    /*
     * Returns a string representation of the card.
     */
    public String toString() {
        return rank.charAt(0) + "[" + suit.charAt(0) + "]";
    }
}
