package game;
import java.util.ArrayList;
import java.util.List;

public class Deck {

    private List<Card> cards;

    private static final String[] SUITS = { "Hearts", "Diamonds", "Clubs", "Spades" };
    private static final String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A" };
    private static final int DEFAULT_SHUFFLES = 3;


    /**
     * Constructs a new deck of cards.
     */
    public Deck() {
        // Initialize the deck
        cards = new ArrayList<>();

        // Populate the deck
        for (String suit : SUITS) {
            for (String rank : RANKS) {
                cards.add(new Card(suit, rank));
            }   
        }
    }

    /**
     * Constructs a new deck of cards and shuffles the deck if the shuffle flag is set to true.
     * 
     * @param shuffleFlag the flag to shuffle the deck
     */ 
    public Deck(boolean shuffleFlag) {
        this();
     
        if (shuffleFlag) {
            shuffle();
        }
    }

    /*
     * Constructs a new deck of cards and shuffles the deck a specified number of times. 
     * 
     * @param ShuffleFlag the flag to shuffle the deck
     * @param shuffleTimes the number of times to shuffle the deck
     */
    public Deck(boolean ShuffleFlag, int shuffleTimes) {
        this();
        if (ShuffleFlag) {
            shuffle(shuffleTimes);
        }
    }

    /**
     * Returns the deck of cards.
     * 
     * @return the deck of cards
     */
    public List<Card> getCards() {
        return cards;
    }


    /**
     * Shuffles the deck of cards a default number of times.
     */
    public void shuffle () {
        shuffle(DEFAULT_SHUFFLES);
    }

    
    /**
     * Shuffles the deck of cards a specified number of times.
     * 
     * @param times the number of times to shuffle the deck
     */
    public void shuffle(int times) {
        // Shuffle the deck
        for (int i =0; i < times; i++) {
            for (int j = 0; j < cards.size(); j++) {
                int k = (int) (Math.random() * cards.size());
                Card temp = cards.get(j);
                cards.set(j, cards.get(k));
                cards.set(k, temp);
            }
        }
    }

    /* 
     * Draws a card from the deck.
     */
    public Card draw() {
        return this.cards.remove(0);
    }

    /*
     * Returns the number of cards in the deck.
     */
    public int size() {
        return cards.size();
    }

    /*
     * Returns a string representation of the deck of cards.
     */
    public String toString() {
        return cards.toString();
    }
}
