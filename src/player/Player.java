package player;

import board.Position;
import java.util.ArrayList;

public abstract class Player {
    protected boolean wonTheGame;
    protected Position currentPlay;
    protected PlayerType playerType;
    protected ArrayList<Position> plays;

    public abstract Position chooseBoardCoordinateFromEmptyPositions(ArrayList<Position> emptyPositions);

    public boolean isGameWinner() {
        return this.wonTheGame;
    }

    public ArrayList<Position> getPlays() {
        return this.plays;
    }

    public Position getCurrentPlay() {
        return this.currentPlay;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setWonTheGame(boolean wonTheGame) {
        this.wonTheGame = wonTheGame;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
