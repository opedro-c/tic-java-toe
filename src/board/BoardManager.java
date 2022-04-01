package board;

import java.util.ArrayList;

public class BoardManager {
    private ArrayList<ArrayList<Cell>> board;
    public final short BOARD_LENGTH = 3;

    public BoardManager() {
        this.board = new ArrayList<ArrayList<Cell>>();
        for (short i = 0; i < BOARD_LENGTH; i++){
            board.add(new ArrayList<Cell>());
            for (short j = 0; j < BOARD_LENGTH; j++) {
                board.get(i).add(new Cell());
            }
        }
    }

    public void setBoardPosition(Position position, Cell cell) {
        this.board
                .get(position.getXCoordinate())
                .set(position.getYCoordinate(), cell);
    }

    public static void printBoard() {
        //TODO: Display board layout and cells (based on their types)
    }
}
