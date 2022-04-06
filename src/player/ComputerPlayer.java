package player;

import board.Position;

import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player {

    @Override
    public Position chooseBoardCoordinateFromEmptyPositions(ArrayList<Position> emptyPositions) {
        Random random = new Random();
        this.currentPlay = emptyPositions.get(random.nextInt(emptyPositions.size()));
        this.plays.add(this.currentPlay);
        return this.currentPlay;
    }
}
