package game;

import board.BoardManager;
import exceptions.EmptyEntryException;
import exceptions.InvalidMainMenuOption;
import player.ComputerPlayer;
import player.HumanPlayer;
import player.Player;
import player.PlayerType;

import java.util.Arrays;
import java.util.Scanner;

public class GameManager {
    Player humanPlayer = new HumanPlayer();
    Player anotherHumanPlayer = new HumanPlayer();
    Player computerPlayer = new ComputerPlayer();
    BoardManager boardManager = new BoardManager();
    GameState gameState;
    MatchOption matchOption;
    boolean gameDrew;
    boolean gameOver;
    boolean playingAgainstComputer;
    boolean humanPlaysFirst;
    boolean someoneWon;
    boolean userEntryOk;
    boolean wantsToQuitGame;
    boolean needsHelp;
    PlayerType gameWinner;
    String mainMenuOption;
    String message = "";

    public GameManager() {
        this.gameState = GameState.STARTING;
    }

    public void processEvents() {
        if (this.needsUserInput()) {
            this.userEntryOk = false;
            Scanner input = new Scanner(System.in);
            try {
                String userInput = input.next();
                if (this.gameState == GameState.CHOOSING_MAIN_MENU_OPTION)
                    this.chooseMainMenuOption(userInput);
                if (this.gameState == GameState.HELPING)
                    this.needsHelp = false;
            } catch (InvalidMainMenuOption e) {
                this.message = e.getMessage();
            } catch (EmptyEntryException e) {
                this.message = e.getMessage();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        } else if (this.gameState == GameState.QUITTING) {
            this.gameOver = true;
            System.exit(0);
        }
    }

    public void updateGameState() {
        if (this.gameState == GameState.STARTING) {
            this.gameState = GameState.CHOOSING_MAIN_MENU_OPTION;
        } else if (this.gameState == GameState.CHOOSING_MAIN_MENU_OPTION) {
            if (userEntryOk){
                if (playingAgainstComputer) {
                    this.gameState = GameState.PLAYING_AGAINST_PC;
                } else if (wantsToQuitGame) {
                    this.gameState = GameState.QUITTING;
                } else if (needsHelp) {
                    this.gameState = GameState.HELPING;
                } else {
                    this.gameState = GameState.PLAYER_1_PLAYING;
                }
            }
        } else if (this.gameState == GameState.PLAYING_AGAINST_PC) {
            this.gameState = GameState.CHOOSING_TO_PLAY_FIRST;
        } else if (this.gameState == GameState.CHOOSING_TO_PLAY_FIRST) {
            this.gameState = GameState.PLAYER_1_PLAYING;
        } else if (this.gameState == GameState.PLAYER_1_PLAYING) {
            if (userEntryOk) {
                if (someoneWon || gameDrew) {
                    this.gameState = GameState.FINISHING_GAME;
                } else {
                    this.gameState = GameState.PLAYER_2_PLAYING;
                }
            }
        } else if (this.gameState == GameState.PLAYER_2_PLAYING) {
            if (someoneWon || gameDrew) {
                this.gameState = GameState.FINISHING_GAME;
            } else if (userEntryOk) {
                this.gameState = GameState.PLAYER_1_PLAYING;
            }
        } else if (this.gameState == GameState.FINISHING_GAME ||
                this.gameState == GameState.HELPING) {
            this.gameState = GameState.CHOOSING_MAIN_MENU_OPTION;
        }
    }

    public void render() {
        if (this.gameState == GameState.CHOOSING_MAIN_MENU_OPTION) {
            displayWelcomeMessage();
            displayMainMenuOptions();
        } else if (this.gameState == GameState.CHOOSING_TO_PLAY_FIRST) {
            System.out.println("choose if user wants to play first against pc");
        } else if (this.gameState == GameState.HELPING) {
            displayHelpMessage();
        } else if (this.gameState == GameState.QUITTING) {
            displayQuittingMessage();
        }
    }

    public boolean gameIsOver() {
        return this.gameOver;
    }

    private boolean needsUserInput() {
        return
            this.gameState == GameState.CHOOSING_MAIN_MENU_OPTION ||
            this.gameState == GameState.HELPING ||
            (this.gameState == GameState.PLAYER_1_PLAYING ||
            (this.humanPlayer.getPlayerType() == PlayerType.PLAYER_1 ||
            this.anotherHumanPlayer.getPlayerType() == PlayerType.PLAYER_1)) ||
            (this.gameState == GameState.PLAYER_2_PLAYING &&
            (this.humanPlayer.getPlayerType() == PlayerType.PLAYER_2 ||
            this.anotherHumanPlayer.getPlayerType() == PlayerType.PLAYER_2));
    }

    private void chooseMainMenuOption(String userInput) {
        String[] mainMenuOptions = {"1", "2", "3", "4"};
        if (userInput.isEmpty())
            throw new EmptyEntryException();
        if (Arrays.stream(mainMenuOptions).noneMatch((opt) -> opt.equals(userInput)))
            throw new InvalidMainMenuOption(userInput);
        this.userEntryOk = true;
        switch (userInput) {
            case "1" -> {
                this.matchOption = MatchOption.HUMAN_VS_HUMAN;
                this.humanPlayer.setPlayerType(PlayerType.PLAYER_1);
                this.anotherHumanPlayer.setPlayerType(PlayerType.PLAYER_2);
            }
            case "2" -> {
                this.matchOption = MatchOption.HUMAN_VS_PC;
                this.playingAgainstComputer = true;
            }
            case "3" -> this.needsHelp = true;
            case "4" -> this.wantsToQuitGame = true;
        }
    }

    private static void displayWelcomeMessage() {
        System.out.println("===========================");
        System.out.println("  Welcome to Tic-Java-Toe  ");
        System.out.println("===========================");
    }

    private static void displayMainMenuOptions() {
        System.out.println("1. Human Vs Human");
        System.out.println("2. Human Vs PC");
        System.out.println("3. Help");
        System.out.println("4. Quit Game");
        System.out.println("Enter an option > ");
    }

    private static void displayHelpMessage() {
        // TODO: Write a really helpful message to user
        System.out.println("really helpful message here");
    }

    private static void displayQuittingMessage() {
        System.out.println("Bye Bye, thanks for playing!!");
    }
}
