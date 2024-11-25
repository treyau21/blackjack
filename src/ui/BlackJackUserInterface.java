package ui;

import game.BlackJackHand;
import game.PlayerAction;
import player.Player;

public interface BlackJackUserInterface {
  
    /**
     * Initializes the user interface.
     */
    public void init();  

    /*
     * UI actions needed before dealing cards
     */
    public void preDeal();

    /*
     * UI actions needed for dealing cards
     */
    public void deal(BlackJackHand player, BlackJackHand dealer);

    /*
     * UI actions for offering Insurance
     */
    public boolean offerInsurance(BlackJackHand player);

    /*
     * UI actions for checking insurance
     */
    public void checkInsurance();

    /*
     * UI actions for paying insurance
     */
    public void payInsurance();

    /*
     * UI actions for losing insurance
     */
    public void loseInsurance();

    /*
     * UI acions for placing a bet
     */
    public int placeBet(Player player, int lastBet);

    /*
     * UI actions needed for winning
     */
    public void win(boolean isBlackJack);

    /*
     * UI actions needed for losing
     */
    public void lose(boolean isBlackJack, boolean isBusted);

    /*
     * UI actions needed for a push
     */
    public void push(boolean isBlackJack);

    /*
     * UI actions needed for a player hit
     */
    public void playerHit(BlackJackHand player);

    /*
     * Prompt the player for an action on their hand
     */
    public PlayerAction playerAction(BlackJackHand player);

    /*
     * UI actions needed for a dealer hit
     */
    public void dealerHit(BlackJackHand dealer);

    /*
     * Reveals the dealer's hand
     */
    public void dealerHandReveal(BlackJackHand dealer);

    /*
     * Evaluates the game
     */
    public void evaluating(BlackJackHand player, BlackJackHand dealer);

    /*
     * Prompts the player if they want to play again
     */
    public boolean playAgain();

    /*
     * Shows the player's balance
     */
    public void showBalance(Player player);

    /*
     * Exits the user interface
     */
    public void exit();
} 
