package ui;

import java.util.Scanner;

import game.BlackJackHand;
import game.PlayerAction;
import player.Player;

public class BlackJackTextUserInterface implements BlackJackUserInterface {

    Scanner scanner = new Scanner(System.in);

    private enum SuspenseType {
        MAJOR,
        MINOR
    }
    private int majorSuspenseTime = 1000; //ms
    private int minorSuspenseTime = 500; //ms
    
    public void init() {
        System.out.println("Welcome to BlackJack!");
    }

    public void preDeal() {
        System.out.println("Dealing cards...");
        suspense(SuspenseType.MAJOR);
    }

    public void deal(BlackJackHand player, BlackJackHand dealer) {
        System.out.println("Dealer's hand: " + dealer.toString(true));
        suspense(SuspenseType.MINOR);

        System.out.println("Player's hand: " + player.toString(false));
        suspense(SuspenseType.MINOR);
    }

    public boolean offerInsurance(BlackJackHand player) {
        suspense(SuspenseType.MINOR);

        if(player.isBlackJack()) {
            System.out.print("Even Money? (y/n): ");
        } else {
            System.out.print("Would you like to buy insurance [$" + (double) player.getBet()/2 + "]? (y/n): ");
        }
        String input = scanner.nextLine();
        
        return input.equalsIgnoreCase("y");
    }

    public void checkInsurance() {
        suspense(SuspenseType.MINOR);
        System.out.println("Checking for dealer blackjack...");
    }

    public void payInsurance() {
        suspense(SuspenseType.MAJOR);
        System.out.println("Dealer has blackjack - Insurance pays");
    }

    public void loseInsurance() {
        suspense(SuspenseType.MAJOR);
        System.out.println("Dealer does not have blackjack - Insurance lost");
    }

    public int placeBet(Player player) {
        int retVal = 0;
        suspense(SuspenseType.MINOR);
        System.out.print("Place your bet: $");

        try {
            retVal = Integer.parseInt(scanner.nextLine());

            // Check balance
            if(retVal > player.getAccount().getBalance()) {
                System.out.println("Insufficient funds. Please try again.");
                retVal = placeBet(player);
            } else if(retVal <= 0) {
                System.out.println("Invalid input. Please try again.");
                retVal = placeBet(player);
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please try again.");
            retVal = placeBet(player);
        }

        return retVal;
    }

    public void win(boolean isBlackJack) {
        suspense(SuspenseType.MINOR);
        System.out.println("Player wins!" + (isBlackJack ? " BLACKJACK!" : ""));
    }

    public void lose(boolean isBlackJack, boolean isBusted) {
        suspense(SuspenseType.MINOR);
        System.out.println("Dealer wins!" + (isBlackJack ? " BLACKJACK!" : "") + (isBusted ? " Player busted!" : ""));
    }

    public void push(boolean isBlackJack) {
        suspense(SuspenseType.MINOR);
        System.out.println("Push!" + (isBlackJack ? " Both player and dealer have blackjack!" : ""));
    }

    public void playerHit(BlackJackHand player) {
        suspense(SuspenseType.MAJOR);
        System.out.println("Player's hand: " + player.toString(false));
    }

    public PlayerAction playerAction(BlackJackHand player) {
        suspense(SuspenseType.MINOR);

        PlayerAction retVal = PlayerAction.UNKNOWN;

        // Prompt the player for their action
        if(player.isDoubleable()) {
            System.out.print((player.isSoft() ? (player.getValue()-10 + "/") : "") + player.getValue() + " " + "- Do you want to hit, stand, or double? (h/s/d): ");
        } else {
            System.out.print((player.isSoft() ? (player.getValue()-10 + "/") : "") + player.getValue() + " " + "- Do you want to hit or stand? (h/s): ");
        }
        String input = scanner.nextLine();

        retVal = 
            (input.equalsIgnoreCase("h")) ? PlayerAction.HIT :
            (input.equalsIgnoreCase("s")) ? PlayerAction.STAND :
            (input.equalsIgnoreCase("d") && player.isDoubleable()) ? PlayerAction.DOUBLE :
            PlayerAction.UNKNOWN;

        // Recursive prompt on error conditions
        if(retVal == PlayerAction.UNKNOWN) {
            System.out.println("Invalid input. Please try again.");
            retVal = playerAction(player);
        }

        return retVal;
    }

    public void dealerHandReveal(BlackJackHand dealer) {
        suspense(SuspenseType.MAJOR);
        System.out.println("Dealer's hand: " + dealer.toString(false));
    }

    public void dealerHit(BlackJackHand dealer) {
        suspense(SuspenseType.MAJOR);
        System.out.println("Dealer's hand: " + dealer.toString(false));
    }

    public void evaluating(BlackJackHand player, BlackJackHand dealer) {
        suspense(SuspenseType.MINOR);
        System.out.println("Player: " + player.getValue() + " Dealer: " + dealer.getValue());
    }

    public boolean playAgain() {
        suspense(SuspenseType.MINOR);
        System.out.print("\nDo you want to play again? (y/n): ");
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("y");
    }

    public void showBalance(Player player) {
        System.out.println(player.getAccount().toString());
    }

    public void exit() {
        System.out.println("\nThanks for playing!");
        scanner.close();
    }

    private void suspense(SuspenseType sType) {
        try {
            Thread.sleep(sType == SuspenseType.MAJOR ? this.majorSuspenseTime : this.minorSuspenseTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
