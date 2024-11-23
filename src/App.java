import game.BlackJackGame;
import ui.*;

public class App {

    private enum GameType { TEXT, GUI }

    private static BlackJackGame game;
    private static BlackJackUserInterface ui;

    private static GameType gameType = GameType.TEXT;

    public static void main(String[] args) throws Exception {
        if(gameType == GameType.TEXT) {
            ui = new BlackJackTextUserInterface();
        } else if(gameType == GameType.GUI) {
            //ui = new BlackJackGUIUserInterface();
        }

        game = new BlackJackGame(ui);
        game.play();
    }
}
