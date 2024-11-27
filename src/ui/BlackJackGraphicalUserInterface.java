package ui;

import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.sound.sampled.*;

import game.BlackJackHand;
import game.PlayerAction;
import player.Player;

public class BlackJackGraphicalUserInterface extends Frame implements BlackJackUserInterface {

    private enum SuspenseType {
        MAJOR,
        MINOR
    }
    private int majorSuspenseTime = 0; //ms
    private int minorSuspenseTime = 0; //ms

    /**
     * Initializes the user interface.
     */
    public void init() {

        // frame size 300 width and 300 height    
        setSize(1200,800);  

        // setting the title of Frame  
        setTitle("Blackjack");   
            
        // no layout manager   
        setLayout(null);   

        // now frame will be visible, by default it is not visible    
        setVisible(true);

        suspense(SuspenseType.MAJOR);

        // Audio Test
        try {
            File soundFile = new File("assets/sounds/test.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();

            while(clip.isRunning()) {
                Thread.sleep(100);
            }

            clip.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
     * UI actions needed before dealing cards
     */
    public void preDeal() {

    }

    /*
     * UI actions needed for dealing cards
     */
    public void deal(BlackJackHand player, BlackJackHand dealer) {

    }

    /*
     * UI actions for offering Insurance
     */
    public boolean offerInsurance(BlackJackHand player) {
        return false;
    }

    /*
     * UI actions for checking insurance
     */
    public void checkInsurance() {

    }

    /*
     * UI actions for paying insurance
     */
    public void payInsurance() {

    }

    /*
     * UI actions for losing insurance
     */
    public void loseInsurance() {

    }

    /*
     * UI acions for placing a bet
     */
    public int placeBet(Player player, int lastBet) {
        return 0;
    }

    /*
     * UI actions needed for winning
     */
    public void win(boolean isBlackJack) {

    }

    /*
     * UI actions needed for losing
     */
    public void lose(boolean isBlackJack, boolean isBusted) {

    }

    /*
     * UI actions needed for a push
     */
    public void push(boolean isBlackJack) {

    }

    /*
     * UI actions needed for a player hit
     */
    public void playerHit(BlackJackHand player) {

    }

    /*
     * Prompt the player for an action on their hand
     */
    public PlayerAction playerAction(BlackJackHand player) {
        return PlayerAction.STAND;
    }

    /*
     * UI actions needed for a dealer hit
     */
    public void dealerHit(BlackJackHand dealer) {

    }

    /*
     * Reveals the dealer's hand
     */
    public void dealerHandReveal(BlackJackHand dealer) {

    }

    /*
     * Evaluates the game
     */
    public void evaluating(BlackJackHand player, BlackJackHand dealer) {

    }

    /*
     * Prompts the player if they want to play again
     */
    public boolean playAgain() {
        return false;
    }

    /*
     * Shows the player's balance
     */
    public void showBalance(Player player) {

    }

    /*
     * Exits the user interface
     */
    public void exit() {

    }

    private void suspense(SuspenseType sType) {
        try {
            Thread.sleep(sType == SuspenseType.MAJOR ? this.majorSuspenseTime : this.minorSuspenseTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
