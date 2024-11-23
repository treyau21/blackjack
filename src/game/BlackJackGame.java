package game;

import player.Player;
import ui.BlackJackUserInterface;

public class BlackJackGame {

    private BlackJackUserInterface ui;
    private Player player;

    private Deck deck;
    private BlackJackHand playersHand;
    private BlackJackHand dealersHand;

    private final double BLACKJACK_PAYOUT = 1.5;
    private final double INSURANCE_PAYOUT = 2.0;

    /*
     * Constructs a new game of BlackJack.
     */
    public BlackJackGame(BlackJackUserInterface ui) {
        this.player = new Player("Player 1", 1000);
        this.ui = ui;

        // Initialize the game
        deck = new Deck();
        ui.init();
    }

    /* 
     * Plays the game of BlackJack (inclusive of the gameLoop logic)
     */
    public void play() {
        boolean promptFlag = false;

        // Game loop
        while (promptFlag = gamePrompt(promptFlag)) {
            deck = new Deck(true);
            deal();

            // Insurance must be offered before checking for blackjacks
            if(dealersHand.isInsurable() && ui.offerInsurance(playersHand)) {
                buyInsurance();
                ui.checkInsurance();
                
                if(this.dealersHand.isBlackJack()) {
                    payInsurance();
                } else {
                    loseInsurance();
                }
            } 
            
            if(true) {
                // Checking for blackjacks
                if(playersHand.isBlackJack() && dealersHand.isBlackJack()) {
                    ui.dealerHandReveal(dealersHand);
                    push();
                } else if(playersHand.isBlackJack()) {
                    ui.dealerHandReveal(dealersHand);
                    win();
                } else if(dealersHand.isBlackJack()) {
                    ui.dealerHandReveal(dealersHand);
                    lose();
                } else {
                    playersTurn();
                    ui.dealerHandReveal(dealersHand);

                    if(!playersHand.isBusted()) {
                        dealersTurn();
                    }

                    evaluateGame();
                }
            } 
        }

        endGame();
    }

    /* 
     * Handles the player's turn.
     */
    private void playersTurn() {
        boolean promptFlag = true;

        while (promptFlag) {
            PlayerAction action = ui.playerAction(playersHand);

            if(action == PlayerAction.HIT) {
                playersHand.deal();
                ui.playerHit(playersHand);

                if (playersHand.isBusted() || playersHand.getValue() == 21) {
                    promptFlag = false; // exit the loop
                }
            } else if(action == PlayerAction.DOUBLE) {
                playersHand.deal();
                player.getAccount().withdraw(playersHand.getBet()); // TODO: Needs an account check
                playersHand.doubleDown();
                ui.playerHit(playersHand);

                promptFlag = false; // exit the loop
            } else { // stand
                promptFlag = false; // exit the loop
            }
        }
    }

    /* 
     * Handles the dealer's turn.
     */
    private void dealersTurn() {
        while (dealersHand.getValue() < 17 || (dealersHand.isSoft() && dealersHand.getValue() == 17)) {
            dealersHand.deal();
            ui.dealerHit(dealersHand);
        }
    }

    /* 
     * Prompts the user to play again.
     * 
     * @param promptFlag the flag to prompt the user to play again
     */
    private boolean gamePrompt(boolean promptFlag) {
        boolean retVal = true;

        // Prompt the user to play again
        if (promptFlag) {
            retVal = ui.playAgain();
        }

        return retVal;
    }

    /*
     * Deals the cards to the players and the dealer.
     */
    private void deal() {
        playersHand = new BlackJackHand(deck);
        dealersHand = new BlackJackHand(deck);

        ui.showBalance(player);
        int bet = ui.placeBet(player);
        playersHand.setBet(bet);
        player.getAccount().withdraw(bet);

        ui.preDeal();

        playersHand.deal();
        dealersHand.deal();
        playersHand.deal();
        dealersHand.deal();

        ui.deal(playersHand, dealersHand);
    }

    /* 
     * Handles the player's winning conditions.
     */
    private void win() {
        ui.win(this.playersHand.isBlackJack());

        int bet = this.playersHand.getBet();
        this.player.getAccount().deposit(bet + (bet * (this.playersHand.isBlackJack() ? this.BLACKJACK_PAYOUT : 1)));
    }

    /* 
     * Handles the dealer's winning conditions.
     */
    private void lose() {
        ui.lose(this.dealersHand.isBlackJack(), this.playersHand.isBusted());
    }

    /* 
     * Handles the push condition.
     */
    private void push() {
        ui.push(this.playersHand.isBlackJack());
        this.player.getAccount().deposit(this.playersHand.getBet());
    }

    /* 
     * Handles the player buying insurance.
     */
    private void buyInsurance() {
        double insurance = (int) playersHand.getBet() / 2;
        player.getAccount().withdraw(insurance); // TODO: Needs an account check
        playersHand.insure(insurance);
    }

    private void payInsurance() {
        player.getAccount().deposit(playersHand.getInsuredAmount() + (playersHand.getInsuredAmount() * INSURANCE_PAYOUT));
        ui.payInsurance();
    }

    private void loseInsurance() {
        ui.loseInsurance();
    }

    /* 
     * Evaluates the game.
     */
    private void evaluateGame(){
        ui.evaluating(playersHand, dealersHand);

        if(playersHand.isBusted()) {
            lose();
        } else if(dealersHand.isBusted() || playersHand.getValue() > dealersHand.getValue()) {
            win();
        } else if(playersHand.getValue() == dealersHand.getValue()){
            push();
        } else {
            lose();
        }
    }

    /* 
     * Ends the game.
     */
    private void endGame() {
        ui.exit();
    }
}
