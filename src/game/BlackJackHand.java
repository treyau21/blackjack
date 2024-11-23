package game;
import java.util.ArrayList;
import java.util.List;

public class BlackJackHand {

    private Deck deck;
    private List<Card> cards;

    private int value = 0;
    private boolean blackjack = false;
    private boolean busted = false;

    // Soft Handlers
    private int fullValueAces = 0;

    // Double Handlers
    private boolean doubleable = false;
    private boolean doubled = false;

    // Insurance Handlers
    private boolean insurable = false;
    private double insuredAmount = 0;

    private int bet;

    private static final int BLACKJACK = 21;

    public BlackJackHand(Deck deck) {
        this.deck = deck;
        this.cards = new ArrayList<Card>();
        this.bet = 0;
    }

    public void deal() {
        // Reset flags that change per card draw
        this.fullValueAces = 0;

        cards.add(deck.draw());

        // Update hand values
        this.value = this.score();
        this.blackjack = (this.value == BLACKJACK && this.cards.size() == 2);
        this.busted = (this.value > BLACKJACK);
        if(this.cards.size() == 2) {
            this.doubleable = true;
            this.insurable = (this.cards.get(1).getValue() == 1);
        }
    }

    private int score() {
        int score = 0;
        int aces = 0;
        
        // Calculate the base score (aces are 1)
        for (Card card : cards) {
            int value = card.getValue();
            if (value == 1) {
                aces++;
            }
            score += value;
        }

        // Update the score for aces
        for(int i = 0; i < aces; i++) {
            if (score + 10 <= 21) {
                score += 10;
                this.fullValueAces++;
            }
        }

        return score;
    }

    public int getBet() {
        return this.bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public boolean isBlackJack() {
        return this.blackjack;
    }

    public boolean isInsurable() {
        return this.insurable;
    }

    public void insure(double amount) {
        this.insuredAmount = amount;
    }

    public boolean isInsured() {
        return this.insuredAmount > 0;
    }

    public double getInsuredAmount() {
        return this.insuredAmount;
    }

    public boolean isBusted() {
        return this.busted;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isSoft() {
        return this.fullValueAces > 0;
    }

    public boolean isDoubleable() {
        return this.doubleable;
    }

    public boolean isDoubled() {
        return this.doubled;
    }

    public void doubleDown() {
        this.doubled = true;
        this.bet *= 2;
    }

    public String toString(boolean hideUpcard) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cards.size(); i++) {
            if (i == 0 && hideUpcard) {
                sb.append("XXXX ");
            } else {
                sb.append(cards.get(i).toString());
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
