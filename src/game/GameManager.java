package game;

import board.BoardManager;
import player.ComputerPlayer;
import player.HumanPlayer;
import player.Player;
import player.PlayerType;

public class GameManager {
    Player humanPlayer = new HumanPlayer();
    Player anotherHumanPlayer = new HumanPlayer();
    Player computerPlayer = new ComputerPlayer();
    BoardManager boardManager = new BoardManager();
    GameState gameState;
    boolean gameDrew;
    boolean gameOver;
    boolean playingAgainstComputer;
    boolean humanPlaysFirst;
    boolean someoneWon;
    PlayerType gameWinner;

    public GameManager() {
        this.gameState = GameState.STARTING;
    }

    void processEvents() {
        // TODO
    }

    void updateGameState() {
        if (this.gameState == GameState.STARTING) {
            this.gameState = GameState.CHOOSING_MAIN_MENU_OPTION;
        } else if (this.gameState == GameState.CHOOSING_MAIN_MENU_OPTION) {
            if (playingAgainstComputer) {
                this.gameState = GameState.CHOOSING_TO_PLAY_FIRST;
            } else {
                this.gameState = GameState.PLAYER_1_PLAYING;
            }
        } else if (this.gameState == GameState.CHOOSING_TO_PLAY_FIRST) {
            this.gameState = GameState.PLAYER_1_PLAYING;
        } else if (this.gameState == GameState.PLAYER_1_PLAYING) {
            if (someoneWon || gameDrew) {
                this.gameState = GameState.FINISHING_GAME;
            } else {
                this.gameState = GameState.PLAYER_2_PLAYING;
            }
        } else if (this.gameState == GameState.PLAYER_2_PLAYING) {
            if (someoneWon || gameDrew) {
                this.gameState = GameState.FINISHING_GAME;
            } else {
                this.gameState = GameState.PLAYER_1_PLAYING;
            }
        } else if (this.gameState == GameState.FINISHING_GAME) {
            this.gameState = GameState.CHOOSING_MAIN_MENU_OPTION;
        }
    }

    void render() {
        // TODO
    }

    boolean gameIsOver() {
        return this.gameOver;
    }
}
